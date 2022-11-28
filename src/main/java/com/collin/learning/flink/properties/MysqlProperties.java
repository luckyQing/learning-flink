package com.collin.learning.flink.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * mysql配置属性
 *
 * @author collin
 * @date 2022-11-28
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "flink.mysql")
public class MysqlProperties {

    /**
     * mysql server地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 监听的数据库
     */
    private String[] databases;

    /**
     * 监听的表
     */
    private String[] tables;

}