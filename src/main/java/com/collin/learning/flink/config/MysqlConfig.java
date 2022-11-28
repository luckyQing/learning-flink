package com.collin.learning.flink.config;

/**
 * mysql配置
 *
 * @author collin
 * @date 2022-11-26
 */
public interface MysqlConfig {

    /**
     * 数据库
     */
    String[] DATABASE_LIST = {"learning_flink"};
    /**
     * 表名
     */
    String[] TABLE_LIST = {"learning_flink.t_product_info", "learning_flink.t_order_bill", "learning_flink.t_user_info"};
    /**
     * server host
     */
    String HOST = "localhost";
    /**
     * mysql端口
     */
    int PORT = 3306;
    /**
     * mysql用户名
     */
    String USERNAME = "root";
    /**
     * mysql密码
     */
    String PASSWORD = "123456";

}