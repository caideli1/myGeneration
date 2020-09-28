CREATE DATABASE IF NOT EXISTS seata_order_0;
CREATE DATABASE IF NOT EXISTS seata_order_1;

CREATE TABLE IF NOT EXISTS seata_order_0.order_0 (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `money` decimal(11,0) DEFAULT NULL COMMENT '订单金额',
  `status` int(1) DEFAULT NULL COMMENT '订单状态：0：创建中；1：已完结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='订单表';

CREATE TABLE IF NOT EXISTS seata_order_1.order_1 (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `money` decimal(11,0) DEFAULT NULL COMMENT '订单金额',
  `status` int(1) DEFAULT NULL COMMENT '订单状态：0：创建中；1：已完结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='订单表';


CREATE TABLE IF NOT EXISTS seata_order_0.undo_log (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `branch_id` bigint(20) NOT NULL COMMENT '分支ID',
  `xid` varchar(100) NOT NULL COMMENT '全局唯一事务id',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息',
  `log_status` int(11) NOT NULL COMMENT '状态',
  `log_created` datetime DEFAULT NULL COMMENT '创建时间',
  `log_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展',
  `context` varchar(100) NOT NULL COMMENT '序列化方式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=618681 DEFAULT CHARSET=utf8 COMMENT='事务回滚日志';


CREATE TABLE IF NOT EXISTS seata_order_1.undo_log (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `branch_id` bigint(20) NOT NULL COMMENT '分支ID',
  `xid` varchar(100) NOT NULL COMMENT '全局唯一事务id',
  `rollback_info` longblob NOT NULL COMMENT '回滚信息',
  `log_status` int(11) NOT NULL COMMENT '状态',
  `log_created` datetime DEFAULT NULL COMMENT '创建时间',
  `log_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展',
  `context` varchar(100) NOT NULL COMMENT '序列化方式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=618681 DEFAULT CHARSET=utf8 COMMENT='事务回滚日志';