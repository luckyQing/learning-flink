package com.collin.learning.flink.test;

import com.collin.learning.flink.config.MysqlConfig;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkTest {

    public static void main(String[] args) throws Exception {
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
                .setParallelism(MysqlConfig.TABLE_LIST.length)
                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering


        env.execute("Print MySQL Snapshot + Binlog");
    }

}