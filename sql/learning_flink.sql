CREATE DATABASE `learning_flink` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';


CREATE TABLE `t_order_bill` (
    `f_id` BIGINT ( 20 ) UNSIGNED NOT NULL,
    `f_order_no` VARCHAR ( 32 ) NOT NULL COMMENT '订单号',
    `f_amount` BIGINT ( 20 ) UNSIGNED NOT NULL COMMENT '订单金额总金额',
    `f_status` TINYINT ( 2 ) UNSIGNED NOT NULL COMMENT '订单状态（1：待扣减库存；2：扣减库存失败；3：抵扣优惠券失败；4：待付款；5：已取消；6：待发货；7：待收货；8：待评价，9：已完成）',
    `f_pay_state` TINYINT ( 1 ) UNSIGNED NOT NULL COMMENT '支付状态（1：待支付；2：支付成功；3：支付失败；4：待退款；5：退款成功；6：退款失败）',
    `f_buyer` BIGINT ( 20 ) UNSIGNED NOT NULL COMMENT '购买人id（demo_user库t_user_info表f_id）',
    `f_sys_insert_time` datetime NOT NULL COMMENT '创建时间',
    `f_sys_upd_time` datetime DEFAULT NULL COMMENT '更新时间',
    `f_sys_del_time` datetime DEFAULT NULL COMMENT '删除时间',
    `f_sys_insert_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '新增者',
    `f_sys_upd_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '更新者',
    `f_sys_del_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '删除者',
    `f_sys_del_state` TINYINT ( 1 ) UNSIGNED NOT NULL DEFAULT '1' COMMENT '删除状态=={1:正常, 2:已删除}',
    PRIMARY KEY ( `f_id` ),
    KEY `uk_order_no` ( `f_order_no` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '订单信息';


CREATE TABLE `t_product_info` (
  `f_id` BIGINT ( 20 ) UNSIGNED NOT NULL,
  `f_name` VARCHAR ( 100 ) NOT NULL COMMENT '商品名称',
  `f_sell_price` BIGINT ( 20 ) UNSIGNED NOT NULL COMMENT '销售价格（单位：万分之一元）',
  `f_discount` DECIMAL ( 20, 2 ) DEFAULT NULL COMMENT '折扣',
  `f_stock` BIGINT ( 20 ) UNSIGNED NOT NULL COMMENT '库存',
  `f_sys_insert_time` datetime NOT NULL COMMENT '创建时间',
  `f_sys_upd_time` datetime DEFAULT NULL COMMENT '更新时间',
  `f_sys_del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `f_sys_insert_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '新增者',
  `f_sys_upd_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '更新者',
  `f_sys_del_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '删除者',
  `f_sys_del_state` TINYINT ( 1 ) UNSIGNED NOT NULL DEFAULT '1' COMMENT '删除状态=={1:正常, 2:已删除}',
  PRIMARY KEY ( `f_id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '商品信息';


CREATE TABLE `t_user_info` (
   `f_id` BIGINT ( 20 ) UNSIGNED NOT NULL,
   `f_mobile` CHAR ( 11 ) DEFAULT NULL COMMENT '手机号',
   `f_nick_name` VARCHAR ( 45 ) DEFAULT NULL COMMENT '昵称',
   `f_real_name` VARCHAR ( 45 ) DEFAULT NULL COMMENT '真实姓名',
   `f_sex` TINYINT ( 1 ) UNSIGNED DEFAULT NULL COMMENT '性别=={"1":"男","2":"女","3":"未知"}',
   `f_birthday` date DEFAULT NULL COMMENT '出生年月',
   `f_profile_image` VARCHAR ( 255 ) DEFAULT NULL COMMENT '头像',
   `f_channel` TINYINT ( 1 ) UNSIGNED NOT NULL COMMENT '所在平台=={"1":"app","2":"web后台","3":"微信"}',
   `f_sys_insert_time` datetime NOT NULL COMMENT '新增时间',
   `f_sys_upd_time` datetime DEFAULT NULL COMMENT '更新时间',
   `f_sys_del_time` datetime DEFAULT NULL COMMENT '删除时间',
   `f_sys_insert_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '新增者',
   `f_sys_upd_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '修改者',
   `f_sys_del_user` BIGINT ( 20 ) UNSIGNED DEFAULT NULL COMMENT '删除者',
   `f_sys_del_state` TINYINT ( 1 ) UNSIGNED NOT NULL DEFAULT '1' COMMENT '记录状态=={"1":"正常","2":"已删除"}',
   PRIMARY KEY ( `f_id` ),
   UNIQUE KEY `f_mobile` ( `f_mobile` )
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 ROW_FORMAT = COMPACT COMMENT = '用户信息';



INSERT INTO `t_product_info` ( `f_id`, `f_name`, `f_sell_price`, `f_discount`, `f_stock`, `f_sys_insert_time`, `f_sys_upd_time`, `f_sys_del_time`, `f_sys_insert_user`, `f_sys_upd_user`, `f_sys_del_user`, `f_sys_del_state` )
VALUES
    ( 25, 'iphone', 100, NULL, 20, '2022-11-26 10:59:36', NULL, NULL, NULL, NULL, NULL, 1 ),
    ( 26, 'iphone', 100, 0.62, 20, '2022-11-26 10:59:36', NULL, NULL, NULL, NULL, NULL, 1 ),
    ( 260, 'iphone000', 100, NULL, 20, '2022-11-26 10:59:36', NULL, NULL, NULL, NULL, NULL, 1 );