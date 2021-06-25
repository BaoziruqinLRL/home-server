/*
 Navicat Premium Data Transfer

 Source Server         : 67.230.184.225
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 67.230.184.225:58005
 Source Schema         : home

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 16/04/2021 15:21:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `auth_dictionary`;
CREATE TABLE `auth_dictionary`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '权限描述',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '权限代码',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户权限字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_dictionary
-- ----------------------------
INSERT INTO `auth_dictionary` VALUES (1, '神', 'god', NULL, NULL, '2019-09-17 03:06:41', '2019-09-17 03:06:41');

-- ----------------------------
-- Table structure for auth_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `auth_menu_relation`;
CREATE TABLE `auth_menu_relation`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `auth_id` bigint(20) NOT NULL COMMENT '权限id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限菜单关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_menu_relation
-- ----------------------------
INSERT INTO `auth_menu_relation` VALUES (1, 1, 1, NULL, NULL, '2019-09-17 03:07:14', '2019-09-17 03:07:14');
INSERT INTO `auth_menu_relation` VALUES (2, 1, 2, NULL, NULL, '2019-09-17 03:07:14', '2019-09-17 03:07:14');
INSERT INTO `auth_menu_relation` VALUES (3, 1, 3, NULL, NULL, '2019-09-17 03:07:14', '2019-09-17 03:07:14');
INSERT INTO `auth_menu_relation` VALUES (4, 1, 4, NULL, NULL, '2019-09-17 03:07:14', '2019-09-17 03:07:14');
INSERT INTO `auth_menu_relation` VALUES (5, 1, 5, NULL, NULL, '2019-09-17 03:07:14', '2019-09-17 03:07:14');
INSERT INTO `auth_menu_relation` VALUES (6, 1, 6, NULL, NULL, '2019-09-18 08:18:18', '2019-09-18 08:18:18');
INSERT INTO `auth_menu_relation` VALUES (7, 1, 7, NULL, NULL, '2019-09-18 08:18:18', '2019-09-18 08:18:18');
INSERT INTO `auth_menu_relation` VALUES (8, 1, 8, NULL, NULL, '2019-09-18 08:18:18', '2019-09-18 08:18:18');
INSERT INTO `auth_menu_relation` VALUES (9, 1, 9, NULL, NULL, '2019-11-04 03:02:09', '2019-11-04 03:02:09');

-- ----------------------------
-- Table structure for email_job
-- ----------------------------
DROP TABLE IF EXISTS `email_job`;
CREATE TABLE `email_job`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名',
  `sender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件发件人',
  `auth_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件发件授权码',
  `subject` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '邮件主题',
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '邮件文本',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '邮件模板id',
  `last_run_time` datetime(0) NULL DEFAULT NULL COMMENT '上次运行任务时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `type` tinyint(4) NULL DEFAULT NULL,
  `receiver` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件任务列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_job
-- ----------------------------
INSERT INTO `email_job` VALUES (10, '脑婆丫丫丫丫', '1239436317@qq.com', 'AU', '嘿，脑婆', '宝儿猪猪乖', NULL, NULL, 10000, NULL, '2019-10-08 09:31:05', '2020-10-22 12:32:16', 1, '[\"529868669@qq.com\"]');
INSERT INTO `email_job` VALUES (11, '哈罗', '1239436317@qq.com', 'AI', 'UIL', 'ashidhuaisdas', NULL, NULL, 10000, NULL, '2019-10-12 01:14:46', '2020-01-10 07:46:04', 2, '[\"529868669@qq.com\"]');
INSERT INTO `email_job` VALUES (12, '该duanlian了', '1239436317@qq.com', 'IO', 'duanlian', '该duanlian了', NULL, NULL, 10000, NULL, '2019-10-12 01:17:18', '2020-01-10 07:46:06', 1, '[\"529868669@qq.com\"]');

-- ----------------------------
-- Table structure for email_job_rule
-- ----------------------------
DROP TABLE IF EXISTS `email_job_rule`;
CREATE TABLE `email_job_rule`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `email_id` bigint(20) NOT NULL COMMENT '邮件任务id',
  `start_time` time(0) NOT NULL COMMENT '开始发送时间点',
  `end_time` time(0) NULL DEFAULT NULL COMMENT '结束发送时间点',
  `start_date` date NOT NULL COMMENT '开始发送日期',
  `end_date` date NULL DEFAULT NULL COMMENT '结束发送日期',
  `interval` int(11) NOT NULL COMMENT '发送间隔,单位分钟',
  `rule_sort` int(11) NOT NULL COMMENT '规则序号',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件发件时间规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_job_rule
-- ----------------------------
INSERT INTO `email_job_rule` VALUES (3, 10, '02:00:00', '23:00:00', '2019-08-01', '2110-10-22', 25, 1, 10000, NULL, '2019-10-08 09:31:14', '2020-10-22 12:31:59');
INSERT INTO `email_job_rule` VALUES (11, 11, '00:00:00', NULL, '2019-07-01', '2111-11-11', 60, 1, 10000, NULL, '2019-10-12 01:14:46', '2019-10-12 01:14:46');
INSERT INTO `email_job_rule` VALUES (12, 12, '20:00:00', '21:00:00', '2019-01-10', '2210-01-01', 60, 1, 10000, NULL, '2019-10-12 01:17:18', '2019-10-12 01:17:18');
INSERT INTO `email_job_rule` VALUES (13, 11, '00:00:00', NULL, '2112-07-01', '2211-11-11', 50, 2, NULL, NULL, '2019-10-12 02:43:05', '2020-04-12 08:30:57');

-- ----------------------------
-- Table structure for family
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组名称',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家庭表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family
-- ----------------------------
INSERT INTO `family` VALUES (1, '丫丫二豆家', NULL, NULL, '2019-09-16 07:11:27', '2019-09-16 07:11:27');

-- ----------------------------
-- Table structure for family_member_relation
-- ----------------------------
DROP TABLE IF EXISTS `family_member_relation`;
CREATE TABLE `family_member_relation`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `family_id` bigint(20) NOT NULL COMMENT '家庭id',
  `member_id` bigint(20) NOT NULL COMMENT '成员id',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '家庭成员关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family_member_relation
-- ----------------------------
INSERT INTO `family_member_relation` VALUES (1, 1, 1, NULL, NULL, '2019-09-16 07:12:13', '2019-09-16 07:12:13');
INSERT INTO `family_member_relation` VALUES (2, 1, 2, NULL, NULL, '2019-09-16 07:12:13', '2019-09-16 07:12:13');
INSERT INTO `family_member_relation` VALUES (3, 1, 3, NULL, NULL, '2019-09-16 07:12:13', '2019-09-16 07:12:13');

-- ----------------------------
-- Table structure for jk_info
-- ----------------------------
DROP TABLE IF EXISTS `jk_info`;
CREATE TABLE `jk_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'jk名称',
  `style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '风格',
  `color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '颜色',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注描述',
  `preview_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预览图片',
  `full_img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '完整图片列表',
  `obj_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'obj模型',
  `mtl_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'mtl模型',
  `sale_time` datetime(0) NULL DEFAULT NULL COMMENT '售卖时间',
  `buy_time` datetime(0) NULL DEFAULT NULL COMMENT '购买时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'jk信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jk_info
-- ----------------------------
INSERT INTO `jk_info` VALUES (1, '绿森林', '绿色格子', '', 120.00, 1, NULL, '/Users/admin/Downloads/jk/a-preview.jpeg', NULL, NULL, NULL, '2019-10-09 00:00:00', NULL, NULL, NULL, '2019-11-13 09:16:01', '2019-11-15 09:57:13');
INSERT INTO `jk_info` VALUES (2, '1', '1', '蓝色,绿色', 1.00, 1, NULL, '/Users/admin/Downloads/jk/5ae9253a89e0d2ac896f40c7955c20eb6152f3b45e17c-XStPWH_fw658.jpeg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-11-14 08:30:39', '2019-11-15 10:04:21');
INSERT INTO `jk_info` VALUES (3, '2', '3', '绿色', 3.00, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-11-14 08:31:18', '2019-11-14 08:31:18');
INSERT INTO `jk_info` VALUES (4, '2', '3', '4', 55.00, 1, NULL, NULL, NULL, NULL, NULL, '2019-11-19 00:00:00', '2019-11-20 00:00:00', NULL, NULL, '2019-11-15 09:17:30', '2019-11-15 09:17:30');
INSERT INTO `jk_info` VALUES (5, '黑森林', '暗黑', '', 78.00, 1, NULL, '/Users/admin/Downloads/jk/th-3.jpeg', NULL, NULL, NULL, '2019-12-04 00:00:00', '2019-11-15 05:22:35', NULL, NULL, '2019-11-15 09:22:41', '2019-11-18 03:52:29');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成员名称',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成员昵称',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, 'yy', '丫丫', NULL, NULL, '2019-09-16 07:11:09', '2019-09-16 07:11:09');
INSERT INTO `member` VALUES (2, 'gd', '果冻', NULL, NULL, '2019-09-16 07:11:09', '2019-09-16 07:11:09');
INSERT INTO `member` VALUES (3, 'ed', '二豆', NULL, NULL, '2019-09-16 07:11:09', '2019-09-16 07:11:09');

-- ----------------------------
-- Table structure for menu_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `menu_dictionary`;
CREATE TABLE `menu_dictionary`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '菜单名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '菜单代码',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '菜单描述',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_dictionary
-- ----------------------------
INSERT INTO `menu_dictionary` VALUES (1, 0, '食谱', 'food-menu', NULL, NULL, NULL, '2019-09-17 03:06:21', '2019-10-14 06:53:05');
INSERT INTO `menu_dictionary` VALUES (2, 0, '1级菜单', 'one', NULL, NULL, NULL, '2019-09-17 03:06:21', '2019-09-17 03:06:21');
INSERT INTO `menu_dictionary` VALUES (3, 2, '1-1级菜单', 'one-one', NULL, NULL, NULL, '2019-09-17 03:06:21', '2019-09-18 08:40:09');
INSERT INTO `menu_dictionary` VALUES (4, 2, '1-2级菜单', 'one-two', NULL, NULL, NULL, '2019-09-17 03:06:21', '2019-09-18 08:40:09');
INSERT INTO `menu_dictionary` VALUES (5, 2, '1-3级菜单', 'one-three', NULL, NULL, NULL, '2019-09-17 03:06:21', '2019-09-18 08:40:09');
INSERT INTO `menu_dictionary` VALUES (6, 0, '2级菜单', 'two', NULL, NULL, NULL, '2019-09-18 08:17:53', '2019-09-18 08:17:53');
INSERT INTO `menu_dictionary` VALUES (7, 6, '2-1级菜单', 'two-one', NULL, NULL, NULL, '2019-09-18 08:17:53', '2019-09-18 08:40:09');
INSERT INTO `menu_dictionary` VALUES (8, 6, '2-2级菜单', 'two-two', NULL, NULL, NULL, '2019-09-18 08:17:53', '2019-09-18 08:40:09');
INSERT INTO `menu_dictionary` VALUES (9, 0, 'video', 'video-list', NULL, NULL, NULL, '2019-11-04 03:01:56', '2019-11-04 03:07:11');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `contact` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系方式',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆名',
  `login_pwd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆密码',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10000, 'ZERO', '18817877986', '18817877986', 'zero', NULL, NULL, '2019-09-16 07:10:44', '2019-09-16 07:10:44');

-- ----------------------------
-- Table structure for user_auth_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_auth_relation`;
CREATE TABLE `user_auth_relation`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `auth_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_auth_relation
-- ----------------------------
INSERT INTO `user_auth_relation` VALUES (1, 10000, 1, NULL, NULL, '2019-09-17 03:06:51', '2019-09-17 03:06:51');

-- ----------------------------
-- Table structure for user_family_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_family_relation`;
CREATE TABLE `user_family_relation`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `family_id` bigint(20) NOT NULL COMMENT '家庭id',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户家庭关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_family_relation
-- ----------------------------
INSERT INTO `user_family_relation` VALUES (1, 10000, 1, NULL, NULL, '2019-09-16 07:12:24', '2019-09-16 07:12:24');
INSERT INTO `user_family_relation` VALUES (2, 10000, 2, NULL, NULL, '2020-04-12 04:50:50', '2020-04-12 04:50:50');

SET FOREIGN_KEY_CHECKS = 1;
