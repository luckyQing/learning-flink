package com.collin.learning.flink.test;

import com.collin.learning.flink.config.MysqlConfig;
import com.collin.learning.flink.sink.RedisSinkWriter;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.connect.json.DecimalFormat;
import org.apache.kafka.connect.json.JsonConverterConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FlinkTest {

    @Test
    void test() throws Exception {
        Map<String, Object> customConverterConfigs = new HashMap<>();
        customConverterConfigs.put(JsonConverterConfig.DECIMAL_FORMAT_CONFIG, DecimalFormat.NUMERIC.name());

        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname(MysqlConfig.HOST)
                .port(MysqlConfig.PORT)
                .databaseList(MysqlConfig.DATABASE_LIST)
                .tableList(MysqlConfig.TABLE_LIST)
                .username(MysqlConfig.USERNAME)
                .password(MysqlConfig.PASSWORD)
                .deserializer(new JsonDebeziumDeserializationSchema(false, customConverterConfigs))
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.enableCheckpointing(3000)
                .fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
                .setParallelism(MysqlConfig.TABLE_LIST.length)
                .sinkTo((Sink<String>) context -> new RedisSinkWriter());


        env.execute("Print MySQL Snapshot + Binlog");
    }

}