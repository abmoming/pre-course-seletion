/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.107
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : 127.0.0.1:3306
 Source Schema         : course_selection

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 23/02/2022 23:13:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cs_course
-- ----------------------------
DROP TABLE IF EXISTS `cs_course`;
CREATE TABLE `cs_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `class_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上课地址',
  `class_start_time` datetime NULL DEFAULT NULL COMMENT '上课开始时间',
  `class_end_time` datetime NULL DEFAULT NULL COMMENT '上课结束时间',
  `num_people` int(11) NULL DEFAULT NULL COMMENT '课程总人数',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '课程状态：0未开课 1结课，默认0',
  `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除，默认0(否)',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cs_course
-- ----------------------------
INSERT INTO `cs_course` VALUES (1, '测试课程', 'A栋', '2022-02-19 23:31:00', '2022-02-20 00:00:00', 60, 0, 'admin', '2022-02-19 23:31:33', 1, NULL);
INSERT INTO `cs_course` VALUES (3, '测试课程', 'A栋', '2022-02-19 23:31:00', '2022-02-20 00:00:00', 10, 0, 'admin', '2022-02-20 21:54:29', 1, NULL);
INSERT INTO `cs_course` VALUES (4, '测试课程2', 'B栋', '2022-02-20 21:55:00', '2022-02-21 00:00:00', 3, 0, 'admin', '2022-02-20 21:55:09', 1, NULL);
INSERT INTO `cs_course` VALUES (5, '测试课程2', 'B栋', '2022-02-20 21:57:00', '2022-02-21 00:00:00', 3, 0, 'admin', '2022-02-20 21:59:22', 1, NULL);
INSERT INTO `cs_course` VALUES (6, '测试课程2', 'B栋', '2022-02-20 22:00:00', '2022-02-21 00:00:00', 4, 0, 'admin', '2022-02-20 22:00:25', 0, NULL);
INSERT INTO `cs_course` VALUES (7, '测试课程', 'A栋', '2022-02-19 23:31:00', '2022-02-20 00:00:00', 10, 0, 'admin', '2022-02-20 22:23:22', 0, NULL);
INSERT INTO `cs_course` VALUES (8, '测试课程3', 'C栋', '2022-02-23 00:00:00', '2022-02-25 00:00:00', 6, 0, 'admin', '2022-02-22 22:59:12', 0, NULL);

-- ----------------------------
-- Table structure for cs_selection
-- ----------------------------
DROP TABLE IF EXISTS `cs_selection`;
CREATE TABLE `cs_selection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '可以是教师id、学生id',
  `cou_id` int(11) NULL DEFAULT NULL COMMENT '课程id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '选课时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cs_selection
-- ----------------------------
INSERT INTO `cs_selection` VALUES (1, 2, 1, '2022-02-19 23:31:33');
INSERT INTO `cs_selection` VALUES (3, 2, 3, '2022-02-20 21:54:29');
INSERT INTO `cs_selection` VALUES (4, 2, 4, '2022-02-20 21:55:09');
INSERT INTO `cs_selection` VALUES (5, 2, 5, '2022-02-20 21:59:22');
INSERT INTO `cs_selection` VALUES (6, 2, 6, '2022-02-20 22:00:25');
INSERT INTO `cs_selection` VALUES (7, 1, 7, '2022-02-20 22:23:22');
INSERT INTO `cs_selection` VALUES (8, 3, 8, '2022-02-22 22:59:12');
INSERT INTO `cs_selection` VALUES (9, 9, 6, NULL);
INSERT INTO `cs_selection` VALUES (13, 9, 7, NULL);

-- ----------------------------
-- Table structure for cs_user
-- ----------------------------
DROP TABLE IF EXISTS `cs_user`;
CREATE TABLE `cs_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除，默认0(否)',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cs_user
-- ----------------------------
INSERT INTO `cs_user` VALUES (1, 'admin', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_admin', 'admin', '2022-02-13 11:01:02', 0, NULL);
INSERT INTO `cs_user` VALUES (2, 'justin', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_teacher', 'admin', '2022-02-16 20:59:05', 0, NULL);
INSERT INTO `cs_user` VALUES (3, '测试666', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_teacher', 'admin', '2022-02-21 19:29:36', 0, NULL);
INSERT INTO `cs_user` VALUES (4, '测试2', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_teacher', 'admin', '2022-02-21 19:17:01', 0, NULL);
INSERT INTO `cs_user` VALUES (5, '123', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_teacher', NULL, '2022-02-21 19:32:49', 0, NULL);
INSERT INTO `cs_user` VALUES (6, '666', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_teacher', NULL, '2022-02-21 19:34:09', 1, NULL);
INSERT INTO `cs_user` VALUES (7, '777', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_teacher', 'admin', '2022-02-21 19:35:01', 1, NULL);
INSERT INTO `cs_user` VALUES (8, '12321321', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_teacher', 'admin', '2022-02-21 20:32:51', 1, NULL);
INSERT INTO `cs_user` VALUES (9, '111', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_student', 'admin', '2022-02-22 08:58:58', 0, NULL);
INSERT INTO `cs_user` VALUES (10, '12321321', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_student', 'admin', '2022-02-22 08:59:27', 1, NULL);
INSERT INTO `cs_user` VALUES (11, '123123', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_student', 'admin', '2022-02-22 09:00:16', 1, NULL);
INSERT INTO `cs_user` VALUES (13, '66666666666666666', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_student', 'admin', '2022-02-22 09:00:44', 1, '2022-02-22 09:06:11');
INSERT INTO `cs_user` VALUES (14, '123', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_student', 'admin', '2022-02-22 21:59:27', 0, NULL);
INSERT INTO `cs_user` VALUES (15, '777', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_student', 'admin', '2022-02-22 23:33:50', 0, NULL);
INSERT INTO `cs_user` VALUES (16, '12321312312', 'ICy5YqxZB1uWSwcVLSNLcA==', 'ROLE_student', 'admin', '2022-02-22 23:33:54', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
