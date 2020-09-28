CREATE DATABASE IF NOT EXISTS seata_account_0;
CREATE DATABASE IF NOT EXISTS seata_account_1;


CREATE TABLE IF NOT EXISTS seata_account_0.account_0 (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '账户金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='账户表';



CREATE TABLE IF NOT EXISTS seata_account_1.account_1 (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '账户金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='账户表';


CREATE TABLE IF NOT EXISTS seata_account_0.undo_log (
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


CREATE TABLE IF NOT EXISTS seata_account_1.undo_log (
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