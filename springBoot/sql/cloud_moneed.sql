DROP TABLE IF EXISTS `amount_pool`;
CREATE TABLE `amount_pool` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `original_amount` decimal(21,2) DEFAULT '0.00' COMMENT '初始化金额',
                             `amount` decimal(21,2) DEFAULT '0.00' COMMENT '当前金额',
                             `used_amount` decimal(21,2) DEFAULT '0.00' COMMENT '已经使用金额',
                             `status` int DEFAULT 0 COMMENT '状态：0 不通知 1 通知',
                             `create_time` datetime NOT NULL COMMENT '创建时间',
                             `update_time` datetime NOT NULL COMMENT '修改时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

DROP TABLE IF EXISTS `amount_pool_flow`;
CREATE TABLE `amount_pool_flow` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  amount_pool_id int(11) NOT NULL COMMENT '资金池id',
                                  `last_rest_amount` decimal(21,2) DEFAULT '0.00' COMMENT '上一次剩余金额',
                                  `change_amount` decimal(21,2) DEFAULT '0.00' COMMENT '充值金额',
                                  `now_original_amount` decimal(21,2) DEFAULT '0.00' COMMENT '充值后金额',
                                  `user_id` int NOT NULL DEFAULT 0 COMMENT '是平台记操作人id',
                                  `create_time` datetime NOT NULL COMMENT '创建时间',
                                  `update_time` datetime NOT NULL COMMENT '修改时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;