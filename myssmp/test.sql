/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 119.23.57.74:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 27/07/2017 10:20:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user1`;
CREATE TABLE `user1` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user1` VALUES (2, '645666', '456456666');
INSERT INTO `user1` VALUES (3, '567', '567567');
INSERT INTO `user1` VALUES (4, '567567', '567567576');
INSERT INTO `user1` VALUES (6, '666666', '6666');
INSERT INTO `user1` VALUES (7, '67567', '78678');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
