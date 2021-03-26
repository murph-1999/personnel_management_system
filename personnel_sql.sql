/*
 Navicat Premium Data Transfer

 Source Server         : 121.5.101.19
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 121.5.101.19:3306
 Source Schema         : personnel_management

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 25/03/2021 14:53:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'Jack', '1234');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `age` int(11) NOT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (1, 'Rose', 14);
INSERT INTO `person` VALUES (3, 'Alice', 12);
INSERT INTO `person` VALUES (4, 'Mike', 11);
INSERT INTO `person` VALUES (6, 'Tom', 14);
INSERT INTO `person` VALUES (7, 'Amy', 16);
INSERT INTO `person` VALUES (8, 'Judy', 46);
INSERT INTO `person` VALUES (9, 'Rudy', 13);
INSERT INTO `person` VALUES (16, '吴海', 35);

SET FOREIGN_KEY_CHECKS = 1;
