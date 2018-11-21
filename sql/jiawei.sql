/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1.3307
Source Server Version : 50724
Source Host           : localhost:3307
Source Database       : jiawei

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-11-21 19:21:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hi
-- ----------------------------
DROP TABLE IF EXISTS `hi`;
CREATE TABLE `hi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hi
-- ----------------------------
INSERT INTO `hi` VALUES ('1', '11');
INSERT INTO `hi` VALUES ('2', '22');

-- ----------------------------
-- Table structure for linkman
-- ----------------------------
DROP TABLE IF EXISTS `linkman`;
CREATE TABLE `linkman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `birth` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `id_card` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `company` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `ext1` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ext2` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ext3` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `phone` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `qq` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of linkman
-- ----------------------------
INSERT INTO `linkman` VALUES ('1', 'user2', 'user2', '122221', '122221', '122221', '122221', '22', '122221', null, '2018-11-20 21:36:38', null, '122221', '122221', '122221', '1', '737338328', '232323');
INSERT INTO `linkman` VALUES ('3', 'user4', 'user4', null, null, null, null, null, null, null, null, null, null, null, null, '3', null, null);
INSERT INTO `linkman` VALUES ('4', '哈哈哈', null, null, null, null, null, null, null, '2018-11-20 21:28:00', null, null, null, null, null, '1', null, null);
INSERT INTO `linkman` VALUES ('6', 'user2', 'user2', '122221', '122221', '122221', '122221', '22', '122221', '2018-11-20 21:52:33', '2018-11-20 21:53:05', null, '122221', '122221', '122221', '15', null, null);
INSERT INTO `linkman` VALUES ('7', 'user2', null, null, null, null, '122221', null, null, '2018-11-21 09:46:11', null, null, '122221', '122221', '122221', '1', null, null);
INSERT INTO `linkman` VALUES ('8', 'user2', null, null, null, null, '122221', null, null, '2018-11-21 09:46:13', null, null, '122221', '122221', '122221', '1', null, null);
INSERT INTO `linkman` VALUES ('13', 'qwe', null, null, null, null, null, null, null, '2018-11-21 15:14:04', null, null, null, null, null, '1', '123213', '21312');
INSERT INTO `linkman` VALUES ('14', 'yui', null, null, null, null, null, null, null, '2018-11-21 17:13:44', null, null, null, null, null, '1', '123213', '21312');
INSERT INTO `linkman` VALUES ('15', '234', null, null, null, null, null, null, null, '2018-11-21 17:36:19', null, null, null, null, null, '8', '234', '234');
INSERT INTO `linkman` VALUES ('16', '567', null, null, null, null, null, null, null, '2018-11-21 17:36:52', null, null, null, null, null, '9', '567', '567');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `birth` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `id_card` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `company` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `pwd` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` tinyint(1) DEFAULT '1' COMMENT '用户类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_code_uindex` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '2018-06-01', '44058019900010', 'admin', '7438738@crc.com.hk', '1380000000', '广东深圳', 'crc', 'admin', '2018-06-01 00:00:00', null, '0');
INSERT INTO `user` VALUES ('2', 'admin1', '2018-06-01', '44058019900010', 'admin1', '7438738@crc.com.hk', '1380000000', '广东深圳', 'crc', 'admin1', '2018-06-01 00:00:00', null, '0');
INSERT INTO `user` VALUES ('3', 'yuzhifan', null, null, 'yuzhifan', null, null, null, null, 'yuzhifan', '2018-11-20 21:45:26', null, '1');
INSERT INTO `user` VALUES ('4', 'yuzhifan1', null, null, 'yuzhifan1', null, null, null, null, 'yuzhifan1', '2018-11-20 21:51:00', null, '1');
INSERT INTO `user` VALUES ('5', 'yuzhifan2', null, null, 'yuzhifan2', null, null, null, null, 'yuzhifan2', '2018-11-20 21:53:28', null, '1');
INSERT INTO `user` VALUES ('6', 'admin22', null, null, 'admin22', null, null, null, null, 'admin22', '2018-11-21 09:40:14', null, '1');
INSERT INTO `user` VALUES ('7', '林西', null, null, '123', null, null, null, null, '123', '2018-11-21 16:44:21', null, '1');
INSERT INTO `user` VALUES ('8', '123', null, null, '456', null, null, null, null, '456', '2018-11-21 16:45:53', null, '1');
INSERT INTO `user` VALUES ('9', '234', null, null, '234', null, null, null, null, '234', '2018-11-21 17:36:40', null, '1');
INSERT INTO `user` VALUES ('10', 'tt', null, null, 'tt', null, null, null, null, 'tt', '2018-11-21 17:38:10', null, '1');
