/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : wifi_data_analysis

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2018-03-09 17:33:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clean_data_hour
-- ----------------------------
DROP TABLE IF EXISTS `clean_data_hour`;
CREATE TABLE `clean_data_hour` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wid` varchar(255) DEFAULT NULL,
  `mmac` varchar(255) DEFAULT NULL,
  `rate` varchar(255) DEFAULT NULL,
  `wssid` varchar(255) DEFAULT NULL,
  `wmac` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lon` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL COMMENT '手机mac地址（辨别手机的唯一值）',
  `rssi` varchar(255) DEFAULT NULL,
  `ranges` varchar(255) DEFAULT NULL COMMENT '与探针的距离',
  `ts` varchar(255) DEFAULT NULL,
  `tmc` varchar(255) DEFAULT NULL,
  `tc` varchar(255) DEFAULT NULL,
  `ds` varchar(255) DEFAULT NULL,
  `essid0` varchar(255) DEFAULT NULL,
  `essid1` varchar(255) DEFAULT NULL,
  `essid2` varchar(255) DEFAULT NULL,
  `essid3` varchar(255) DEFAULT NULL,
  `essid4` varchar(255) DEFAULT NULL,
  `essid5` varchar(255) DEFAULT NULL,
  `essid6` varchar(255) DEFAULT NULL,
  `countMac` varchar(255) DEFAULT NULL COMMENT '当前小时不重复时间点的出现次数',
  `minTime` varchar(255) DEFAULT NULL COMMENT '当前小时最小时间',
  `maxTime` varchar(255) DEFAULT NULL COMMENT '当前小时最大时间',
  `minRanges` varchar(255) DEFAULT NULL COMMENT '当前小时最小距离',
  `maxRanges` varchar(255) DEFAULT NULL COMMENT '当前小时最大距离',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9907 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for entry_volume
-- ----------------------------
DROP TABLE IF EXISTS `entry_volume`;
CREATE TABLE `entry_volume` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataTime` varchar(255) DEFAULT NULL COMMENT '数据时间点',
  `countMac` int(11) DEFAULT NULL COMMENT '客流量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for guest_flow
-- ----------------------------
DROP TABLE IF EXISTS `guest_flow`;
CREATE TABLE `guest_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dataTime` varchar(255) DEFAULT NULL COMMENT '数据时间点',
  `countMac` int(11) DEFAULT NULL COMMENT '客流量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for guest_flow_day
-- ----------------------------
DROP TABLE IF EXISTS `guest_flow_day`;
CREATE TABLE `guest_flow_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataDate` datetime NOT NULL COMMENT '数据记录时间',
  `zeroOclock` int(11) DEFAULT NULL COMMENT '0点',
  `oneOclock` int(11) DEFAULT NULL COMMENT '1点',
  `twoOclock` int(11) DEFAULT NULL COMMENT '2点',
  `threeOclock` int(11) DEFAULT NULL COMMENT '3点',
  `fourOclock` int(11) DEFAULT NULL COMMENT '4点',
  `fiveOclock` int(11) DEFAULT NULL COMMENT '5点',
  `sixOclock` int(11) DEFAULT NULL,
  `sevenOclock` int(11) DEFAULT NULL,
  `eightOclock` int(11) DEFAULT NULL,
  `nineOclock` int(11) DEFAULT NULL,
  `tenOclock` int(11) DEFAULT NULL,
  `elevenOclock` int(11) DEFAULT NULL,
  `twelveOclock` int(11) DEFAULT NULL,
  `thirteenOclock` int(11) DEFAULT NULL,
  `fourteenOclock` int(11) DEFAULT NULL,
  `fifteenOclock` int(11) DEFAULT NULL,
  `sixteenOclock` int(11) DEFAULT NULL,
  `seventeenOclock` int(11) DEFAULT NULL,
  `eighteenOclock` int(11) DEFAULT NULL,
  `nineteenOclock` int(11) DEFAULT NULL,
  `twetyOclock` int(11) DEFAULT NULL,
  `twetyOneOclock` int(11) DEFAULT NULL,
  `twetyTwoOclock` int(11) DEFAULT NULL,
  `twetyThreeOclock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for guest_mac
-- ----------------------------
DROP TABLE IF EXISTS `guest_mac`;
CREATE TABLE `guest_mac` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataTime` datetime DEFAULT NULL COMMENT '获取数据的时间',
  `wifiId` varchar(255) DEFAULT NULL COMMENT '探针ID',
  `phoneMac` varchar(255) DEFAULT NULL COMMENT '手机mac',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=571 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for old_data
-- ----------------------------
DROP TABLE IF EXISTS `old_data`;
CREATE TABLE `old_data` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `wid` varchar(255) DEFAULT NULL,
  `mmac` varchar(255) DEFAULT NULL,
  `rate` varchar(255) DEFAULT NULL,
  `wssid` varchar(255) DEFAULT NULL,
  `wmac` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lon` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `rssi` varchar(255) DEFAULT NULL,
  `ranges` varchar(255) DEFAULT NULL,
  `ts` varchar(255) DEFAULT NULL,
  `tmc` varchar(255) DEFAULT NULL,
  `tc` varchar(255) DEFAULT NULL,
  `ds` varchar(255) DEFAULT NULL,
  `essid0` varchar(255) DEFAULT NULL,
  `essid1` varchar(255) DEFAULT NULL,
  `essid2` varchar(255) DEFAULT NULL,
  `essid3` varchar(255) DEFAULT NULL,
  `essid4` varchar(255) DEFAULT NULL,
  `essid5` varchar(255) DEFAULT NULL,
  `essid6` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60657 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source_data
-- ----------------------------
DROP TABLE IF EXISTS `source_data`;
CREATE TABLE `source_data` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `wid` varchar(255) DEFAULT NULL,
  `mmac` varchar(255) DEFAULT NULL,
  `rate` varchar(255) DEFAULT NULL,
  `wssid` varchar(255) DEFAULT NULL,
  `wmac` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lon` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `rssi` varchar(255) DEFAULT NULL,
  `ranges` varchar(255) DEFAULT NULL,
  `ts` varchar(255) DEFAULT NULL,
  `tmc` varchar(255) DEFAULT NULL,
  `tc` varchar(255) DEFAULT NULL,
  `ds` varchar(255) DEFAULT NULL,
  `essid0` varchar(255) DEFAULT NULL,
  `essid1` varchar(255) DEFAULT NULL,
  `essid2` varchar(255) DEFAULT NULL,
  `essid3` varchar(255) DEFAULT NULL,
  `essid4` varchar(255) DEFAULT NULL,
  `essid5` varchar(255) DEFAULT NULL,
  `essid6` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=379490 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for source_hour_data
-- ----------------------------
DROP TABLE IF EXISTS `source_hour_data`;
CREATE TABLE `source_hour_data` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `wid` varchar(255) DEFAULT NULL,
  `mmac` varchar(255) DEFAULT NULL,
  `rate` varchar(255) DEFAULT NULL,
  `wssid` varchar(255) DEFAULT NULL,
  `wmac` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lon` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `rssi` varchar(255) DEFAULT NULL,
  `ranges` varchar(255) DEFAULT NULL,
  `ts` varchar(255) DEFAULT NULL,
  `tmc` varchar(255) DEFAULT NULL,
  `tc` varchar(255) DEFAULT NULL,
  `ds` varchar(255) DEFAULT NULL,
  `essid0` varchar(255) DEFAULT NULL,
  `essid1` varchar(255) DEFAULT NULL,
  `essid2` varchar(255) DEFAULT NULL,
  `essid3` varchar(255) DEFAULT NULL,
  `essid4` varchar(255) DEFAULT NULL,
  `essid5` varchar(255) DEFAULT NULL,
  `essid6` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6453 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_action`;
CREATE TABLE `sys_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '功能名称',
  `url` varchar(100) NOT NULL DEFAULT 'javascript:void(0);' COMMENT '功能url',
  `type` int(2) NOT NULL COMMENT '1：系统功能 2：导航菜单',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级菜单id',
  `remark` varchar(200) NOT NULL COMMENT '备注',
  `perms` varchar(255) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `icon` varchar(500) DEFAULT '' COMMENT '图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `weight` int(11) NOT NULL DEFAULT '99' COMMENT '权重 越大越靠前',
  `status` int(11) DEFAULT '1' COMMENT '状态 1:可用 0：不可用 -1：删除',
  `update_time` datetime NOT NULL,
  `parent_name` varchar(20) DEFAULT NULL COMMENT '父级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator_log`;
CREATE TABLE `sys_operator_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_uid` varchar(20) NOT NULL COMMENT '操作人uid',
  `sys_name` varchar(255) NOT NULL COMMENT '操作人名字',
  `operation` varchar(100) NOT NULL COMMENT '操作名字',
  `ip` varchar(20) NOT NULL COMMENT '用户ip地址',
  `params` varchar(1024) NOT NULL COMMENT '操作内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `method` varchar(255) NOT NULL COMMENT '方法名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(200) NOT NULL COMMENT '角色名称',
  `describe` varchar(200) NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_action`;
CREATE TABLE `sys_role_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `action_id` int(11) NOT NULL COMMENT '功能id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_uid` int(11) NOT NULL COMMENT '创建人id',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户uid',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `pswd` varchar(100) NOT NULL COMMENT '用户密码',
  `real_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `qq` varchar(15) DEFAULT NULL COMMENT '联系qq',
  `email` varchar(50) DEFAULT NULL COMMENT '联系邮箱',
  `create_uid` int(11) NOT NULL COMMENT '创建人',
  `type` int(2) NOT NULL COMMENT '1:根用户 2：商户',
  `is_valid` int(1) NOT NULL DEFAULT '0' COMMENT '0 无效 1 有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime NOT NULL COMMENT '最后登录时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `sys_code` varchar(128) DEFAULT 'xdwl' COMMENT '系统码',
  `del_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uniq` (`name`),
  KEY `idx` (`name`,`pswd`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_action
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_action`;
CREATE TABLE `sys_user_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户uid',
  `action_id` int(11) NOT NULL COMMENT '功能id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_uid` int(11) NOT NULL COMMENT '创建人',
  `remark` varchar(200) NOT NULL COMMENT '备注',
  `role_id` int(11) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_1` (`uid`,`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(200) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataTime` varchar(255) DEFAULT NULL,
  `countMac` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
