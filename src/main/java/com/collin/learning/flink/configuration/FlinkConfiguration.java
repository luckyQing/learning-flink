package com.collin.learning.flink.configuration;

import com.collin.learning.flink.properties.MysqlProperties;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.kafka.connect.json.DecimalFormat;
import org.apache.kafka.connect.json.JsonConverterConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(MysqlProperties.class)
public class FlinkConfiguration {

    @Bean
    public MySqlSource<String> mySqlSource(final MysqlProperties mysqlProperties) {
        Map<String, Object> customConverterConfigs = new HashMap<>(1);
        customConverterConfigs.put(JsonConverterConfig.DECIMAL_FORMAT_CONFIG, DecimalFormat.NUMERIC.name());

        return MySqlSource.<String>builder()
                .hostname(mysqlProperties.getHost())
                .port(mysqlProperties.getPort())
                .databaseList(mysqlProperties.getDatabases())
                .tableList(mysqlProperties.getTables())
                .username(mysqlProperties.getUsername())
                .password(mysqlProperties.getPassword())
                .deserializer(new JsonDebeziumDeserializationSchema(false, customConverterConfigs))
                .build();
    }

}