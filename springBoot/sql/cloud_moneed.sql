/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : cloud_moneed

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2022-05-16 18:43:06
*/

SET
FOREIGN_KEY_CHECKS
=
0;

-- ----------------------------
-- Table structure for amount_pool
-- ----------------------------
DROP TABLE IF EXISTS `amount_pool`;
CREATE TABLE `amount_pool`
(
  `id`              int(11) NOT NULL AUTO_INCREMENT,
  `original_amount` decimal(21,2) DEFAULT '' 0.00 '' COMMENT '' 初始化金额 '',
  `amount`          decimal(21,2) DEFAULT '' 0.00 '' COMMENT '' 当前金额 '',
  `used_amount`     decimal(21,2) DEFAULT '' 0.00 '' COMMENT '' 已经使用金额 '',
  `status`          int(11) DEFAULT '' 0 '' COMMENT '' 状态：0 不通知 1 通知 '',
  `img`             varchar(255)  DEFAULT NULL,
  `create_time`     datetime NOT NULL COMMENT '' 创建时间 '',
  `update_time`     datetime NOT NULL COMMENT '' 修改时间 '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of amount_pool
-- ----------------------------
INSERT INTO `amount_pool`
VALUES ('' 1 '', '' 100.00 '', '' 10.00 '', '' 0.00 '', '' 0 '',
        '' http://img.kzyz.net/upload/image/202008/6b2f195e-3abe-4f53-8581-02d0d2796daf.jpg '',
        '' 2022-05-16 17:23:30 '', '' 2022-05-16 17:23:32 '');

-- ----------------------------
-- Table structure for amount_pool_flow
-- ----------------------------
DROP TABLE IF EXISTS `amount_pool_flow`;
CREATE TABLE `amount_pool_flow`
(
  `id`                  int(11) NOT NULL AUTO_INCREMENT,
  `amount_pool_id`      int(11) NOT NULL COMMENT '' 资金池id '',
  `last_rest_amount`    decimal(21,2) DEFAULT '' 0.00 '' COMMENT '' 上一次剩余金额 '',
  `change_amount`       decimal(21,2) DEFAULT '' 0.00 '' COMMENT '' 充值金额 '',
  `now_original_amount` decimal(21,2) DEFAULT '' 0.00 '' COMMENT '' 充值后金额 '',
  `user_id`             int(11) NOT NULL DEFAULT '' 0 '' COMMENT '' 是平台记操作人id '',
  `create_time`         datetime NOT NULL COMMENT '' 创建时间 '',
  `update_time`         datetime NOT NULL COMMENT '' 修改时间 '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of amount_pool_flow
-- ----------------------------
