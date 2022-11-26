package com.collin.learning.flink.test;

import com.collin.learning.flink.config.MysqlConfig;
import com.collin.learning.flink.sink.RedisSinkWriter;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FlinkTest {

    @Test
    void test() throws Exception {
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname(MysqlConfig.HOST)
                .port(MysqlConfig.PORT)
                .databaseList(MysqlConfig.DATABASE_LIST) // set captured database
                .tableList(MysqlConfig.TABLE_LIST) // set captured table
                .username(MysqlConfig.USERNAME)
                .password(MysqlConfig.PASSWORD)
                .deserializer(new JsonDebeziumDeserializationSchema()) // converts SourceRecord to JSON String
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // enable checkpoint
        env.enableCheckpointing(3000)
                .fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
                .setParallelism(MysqlConfig.TABLE_LIST.length).sinkTo((Sink<String>) context -> new RedisSinkWriter());


        env.execute("Print MySQL Snapshot + Binlog");
    }

}