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
    String[] DATABASE_LIST = {"flink_learning"};
    /**
     * 表名
     */
    String[] TABLE_LIST = {"flink_learning.t_product_info", "flink_learning.t_order_bill", "flink_learning.t_user_info"};
    /**
     * server host
     */
    String HOST = "192.168.15.117";
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