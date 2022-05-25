/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : graproject

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 25/05/2022 22:11:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `bid` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `time` datetime(0) NOT NULL,
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `blog_user_id`(`uid`) USING BTREE,
  CONSTRAINT `blog_user_id` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, 1, '文章', '测试', 'test', '2022-02-23 20:15:55');
INSERT INTO `blog` VALUES (4, 2, '文章333', '测试', 'test', '2022-02-23 20:15:55');
INSERT INTO `blog` VALUES (6, 2, '文章333', '测试', 'test', '2022-02-23 20:15:55');
INSERT INTO `blog` VALUES (7, 2, '文章333', '测试', 'test', '2022-02-23 20:15:55');
INSERT INTO `blog` VALUES (10, 2, '文章333', '测试', 'test', '2022-02-23 20:15:55');
INSERT INTO `blog` VALUES (13, 2, '文章333', '测试', 'test', '2022-02-21 05:31:59');
INSERT INTO `blog` VALUES (17, 2, '111', '1111', '1111', '2022-03-09 15:24:52');
INSERT INTO `blog` VALUES (18, 2, '11111', '1111111', '1111111111', '2022-03-09 15:26:13');
INSERT INTO `blog` VALUES (20, 2, '22222', '22222222', '22222222', '2022-03-09 15:34:26');
INSERT INTO `blog` VALUES (22, 2, '22222', '22222222', '2222', '2022-03-15 22:26:37');
INSERT INTO `blog` VALUES (23, 2, '12312', '21331', '213131', '2022-03-15 22:44:19');
INSERT INTO `blog` VALUES (24, 2, 'Login', 'Login接口方法', '# Login\n\n\n\n## 登录\n\n* 方法描述：登录接口\n* URL地址：/login\n* 请求方式：post\n* 请求参数：\n\n| 字段     | 说明   | 类型    | 备注                               | 是否必填 |\n| -------- | ------ | ------- | ---------------------------------- | -------- |\n| uid      | id     | Integer | 请求体参数                         | N        |\n| username | 用户名 | String  | 请求体参数                         | Y        |\n| password | 密码   | String  | 请求体参数                         | Y        |\n| email    | 邮箱   | String  | 请求体参数                         | N        |\n| role     | 角色   | String  | 请求体参数；自动从数据库中获取权限 | N        |\n\n* 返回结果：\n\n```json\n{\n    \"code\": 200,\n    \"data\": {\n        \"uid\": 2,\n        \"role\": \"user\",\n        \"username\": \"superman\",\n        \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDczMzM1NTgsInVzZXJuYW1lIjoic3VwZXJtYW4ifQ.nPB1Q1gaHSqyozypIzP-U1siiY2oTCoHmlq_o4NhCB0\"\n    },\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 登出\n\n* 方法描述：登出接口\n* URL地址：/logout\n* 请求方式：post\n* 请求参数：null\n* 返回结果：\n\n```json\n{\n    \"code\": 200,\n    \"data\": \"退出登录\",\n    \"msg\": \"操作成功\"\n}\n```\n\n', '2022-04-10 14:13:49');
INSERT INTO `blog` VALUES (27, 2, 'Login接口', 'Login接口方法', '# Login\n\n处理登录、登出相关逻辑\n\n## 登录\n\n* 方法描述：登录接口\n* URL地址：/login\n* 请求方式：post\n* 请求参数：\n\n| 字段     | 说明   | 类型    | 备注                               | 是否必填 |\n| -------- | ------ | ------- | ---------------------------------- | -------- |\n| uid      | id     | Integer | 请求体参数                         | N        |\n| username | 用户名 | String  | 请求体参数                         | Y        |\n| password | 密码   | String  | 请求体参数                         | Y        |\n| email    | 邮箱   | String  | 请求体参数                         | N        |\n| role     | 角色   | String  | 请求体参数；自动从数据库中获取权限 | N        |\n\n* 返回结果：\n\n```json\n{\n    \"code\": 200,\n    \"data\": {\n        \"uid\": 2,\n        \"role\": \"user\",\n        \"username\": \"superman\",\n        \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDczMzM1NTgsInVzZXJuYW1lIjoic3VwZXJtYW4ifQ.nPB1Q1gaHSqyozypIzP-U1siiY2oTCoHmlq_o4NhCB0\"\n    },\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 登出\n\n* 方法描述：登出接口\n* URL地址：/logout\n* 请求方式：post\n* 请求参数：null\n* 返回结果：\n\n```json\n{\n    \"code\": 200,\n    \"data\": \"退出登录\",\n    \"msg\": \"操作成功\"\n}\n```\n\n', '2022-03-16 19:07:04');
INSERT INTO `blog` VALUES (30, 2, '2022410', '11111', '111111', '2022-04-10 14:10:16');
INSERT INTO `blog` VALUES (32, 5, 'Account接口', 'Account API详细描述', '# Account\n\n处理管理员管理账号相关逻辑\n\n## 获取所有账号\n\n* 方法描述：管理员删除账号（管理员才有权限）\n* URL地址：/account\n* 请求方式：get\n* 请求参数：null\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": [\n        {\n            \"uid\": 1,\n            \"username\": \"admin\",\n            \"password\": \"123\",\n            \"role\": \"admin\",\n            \"email\": null\n        },\n        {\n            \"uid\": 2,\n            \"username\": \"superman\",\n            \"password\": \"123\",\n            \"role\": \"user\",\n            \"email\": null\n        },\n        {\n            \"uid\": 4,\n            \"username\": \"ironman\",\n            \"password\": \"333\",\n            \"role\": \"user\",\n            \"email\": null\n        },\n        {\n            \"uid\": 5,\n            \"username\": \"batman\",\n            \"password\": \"111\",\n            \"role\": \"user\",\n            \"email\": null\n        }\n    ],\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 更改密码\n\n* 方法描述：管理员根据uid更改密码（管理员才有权限）\n* URL地址：/account/psw\n* 请求方式：put\n* 请求参数：\n\n| 字段     | 说明    | 类型    | 备注                                      | 是否必填 |\n| -------- | ------- | ------- | ----------------------------------------- | -------- |\n| id       | user id | Integer | 请求体参数；接收端在map中获取uid字段      | Y        |\n| password |         | String  | 请求体参数；接收端在map中获取password字段 | Y        |\n\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": null,\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 更改邮箱\n\n* 方法描述：管理员根据uid更改邮箱（管理员才有权限）\n* URL地址：/account/email\n* 请求方式：put\n* 请求参数：\n\n| 字段  | 说明    | 类型    | 备注                                   | 是否必填 |\n| ----- | ------- | ------- | -------------------------------------- | -------- |\n| id    | user id | Integer | 请求体参数；接收端在map中获取uid字段   | Y        |\n| email |         | String  | 请求体参数；接收端在map中获取email字段 | Y        |\n\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": null,\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 删除账号\n\n* 方法描述：管理员删除账号（管理员才有权限）\n* URL地址：/account\n* 请求方式：delete\n* 请求参数：\n\n| 字段 | 说明    | 类型    | 备注     | 是否必填 |\n| ---- | ------- | ------- | -------- | -------- |\n| id   | user id | Integer | 路径参数 | Y        |\n\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": null,\n    \"msg\": \"操作成功\"\n}\n```\n\n', '2022-05-07 12:53:35');
INSERT INTO `blog` VALUES (33, 5, 'Blog接口', '处理博文相关逻辑', '# Blog\n\n处理博文相关逻辑\n\n## 获取所有博文\n\n* 方法描述：获取管理员界面博文对象（管理员才有权限获取）\n* URL地址：/blog/select\n* 请求方式：get\n* 请求参数：null\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": [\n        {\n            \"bid\": 24,\n            \"username\": \"superman\",\n            \"title\": \"Login接口\",\n            \"description\": null,\n            \"time\": \"2022-03-15 22:45:04\"\n        },\n        {\n            \"bid\": 23,\n            \"username\": \"superman\",\n            \"title\": \"12312\",\n            \"description\": null,\n            \"time\": \"2022-03-15 22:44:19\"\n        },\n        {\n            \"bid\": 22,\n            \"username\": \"superman\",\n            \"title\": \"22222\",\n            \"description\": null,\n            \"time\": \"2022-03-15 22:26:37\"\n        },\n        {\n            \"bid\": 20,\n            \"username\": \"superman\",\n            \"title\": \"22222\",\n            \"description\": null,\n            \"time\": \"2022-03-09 15:34:26\"\n        }\n    ],\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 获取首页博文\n\n* 方法描述：获取首页的视图博文分页对象（用户和管理员才有权限获取）\n\n* URL地址：/blog\n* 请求方式：get\n* 请求参数：\n\n| 字段        | 说明     | 类型    | 备注     | 是否必填 |\n| ----------- | -------- | ------- | -------- | -------- |\n| currentPage | 当前页码 | Integer | 请求参数 | Y        |\n| pageSize    | 页面大小 | Integer | 请求参数 | N        |\n\n* 请求结果：\n\n```json\n{\n    \"code\": 200,\n    \"data\": {\n        \"records\": [\n            {\n                \"bid\": 21,\n                \"username\": \"superman\",\n                \"title\": \"1111111\",\n                \"description\": \"12312312\",\n                \"time\": \"2022-03-14 20:56:11\"\n            },\n            {\n                \"bid\": 20,\n                \"username\": \"superman\",\n                \"title\": \"22222\",\n                \"description\": \"22222222\",\n                \"time\": \"2022-03-09 15:34:26\"\n            },\n            {\n                \"bid\": 19,\n                \"username\": \"superman\",\n                \"title\": \"111\",\n                \"description\": \"111\",\n                \"time\": \"2022-03-09 15:29:32\"\n            },\n            {\n                \"bid\": 18,\n                \"username\": \"superman\",\n                \"title\": \"11111\",\n                \"description\": \"1111111\",\n                \"time\": \"2022-03-09 15:26:13\"\n            },\n            {\n                \"bid\": 17,\n                \"username\": \"superman\",\n                \"title\": \"111\",\n                \"description\": \"1111\",\n                \"time\": \"2022-03-09 15:24:52\"\n            }\n        ],\n        \"total\": 14,\n        \"size\": 5,\n        \"current\": 1,\n        \"orders\": [],\n        \"optimizeCountSql\": true,\n        \"hitCount\": false,\n        \"countId\": null,\n        \"maxLimit\": null,\n        \"searchCount\": true,\n        \"pages\": 3\n    },\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 根据Uid获取博文\n\n* 方法描述：获取该用户所发布博文分页对象（用户才有权限获取）\n\n* URL地址：/blog/selectUid\n* 请求方式：post\n* 请求参数：\n\n| 字段        | 说明     | 类型    | 备注                                         | 是否必填 |\n| ----------- | -------- | ------- | -------------------------------------------- | -------- |\n| uid         |          | Integer | 请求体参数；接收端在map中获取uid字段         | Y        |\n| currentPage | 当前页码 | Integer | 请求体参数；接收端在map中获取currentPage字段 | Y        |\n| pageSize    | 页面大小 | Integer | 请求体参数；接收端在map中获取pageSize字段    | Y        |\n\n* 请求结果：\n\n```json\n{\n    \"code\": 200,\n    \"data\": {\n        \"records\": [\n            {\n                \"bid\": 21,\n                \"uid\": 2,\n                \"title\": \"1111111\",\n                \"description\": \"12312312\",\n                \"content\": \"12312312\",\n                \"time\": \"2022-03-14 20:56:11\"\n            },\n            {\n                \"bid\": 20,\n                \"uid\": 2,\n                \"title\": \"22222\",\n                \"description\": \"22222222\",\n                \"content\": \"22222222\",\n                \"time\": \"2022-03-09 15:34:26\"\n            },\n            {\n                \"bid\": 19,\n                \"uid\": 2,\n                \"title\": \"111\",\n                \"description\": \"111\",\n                \"content\": \"11111111\",\n                \"time\": \"2022-03-09 15:29:32\"\n            },\n            {\n                \"bid\": 18,\n                \"uid\": 2,\n                \"title\": \"11111\",\n                \"description\": \"1111111\",\n                \"content\": \"1111111111\",\n                \"time\": \"2022-03-09 15:26:13\"\n            },\n            {\n                \"bid\": 17,\n                \"uid\": 2,\n                \"title\": \"111\",\n                \"description\": \"1111\",\n                \"content\": \"1111\",\n                \"time\": \"2022-03-09 15:24:52\"\n            }\n        ],\n        \"total\": 13,\n        \"size\": 5,\n        \"current\": 1,\n        \"orders\": [],\n        \"optimizeCountSql\": true,\n        \"hitCount\": false,\n        \"countId\": null,\n        \"maxLimit\": null,\n        \"searchCount\": true,\n        \"pages\": 3\n    },\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 根据Bid获取博文\n\n* 方法描述：获取博文详细内容和博文作者（用户才有权限获取）\n* URL地址：/blog/select\n* 请求方式：get\n* 请求参数：\n\n| 字段 | 说明    | 类型    | 备注     | 是否必填 |\n| ---- | ------- | ------- | -------- | -------- |\n| id   | blog id | Integer | 路径参数 | Y        |\n\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": {\n        \"author\": \"superman\",\n        \"blog\": {\n            \"bid\": 3,\n            \"uid\": 2,\n            \"title\": \"文章333\",\n            \"description\": \"测试\",\n            \"content\": \"test\",\n            \"time\": \"2022-02-23 20:15:55\"\n        }\n    },\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 添加博文\n\n* 方法描述：添加博文（用户才有权限添加）\n\n* URL地址：/blog\n* 请求方式：post\n* 请求参数：\n\n| 字段        | 说明     | 类型    | 备注                                         | 是否必填 |\n| ----------- | -------- | ------- | -------------------------------------------- | -------- |\n| bid         | blog id  | Integer | 请求体参数                                   | N        |\n| uid         | user id  | Integer | 请求体参数；接收端在map中获取currentPage字段 | Y        |\n| title       | 博文标题 | Integer | 请求体参数；接收端在map中获取pageSize字段    | Y        |\n| description | 博文描述 | String  | 请求体参数                                   | Y        |\n| content     | 博文内容 | String  | 请求体参数                                   | Y        |\n| time        | 发表时间 | String  | 请求体参数                                   | N        |\n\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": null,\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 修改博文\n\n* 方法描述：根据bid修改博文（用户才有权限修改）\n\n* URL地址：/blog\n* 请求方式：put\n* 请求参数：\n\n| 字段        | 说明     | 类型    | 备注                                         | 是否必填 |\n| ----------- | -------- | ------- | -------------------------------------------- | -------- |\n| bid         | blog id  | Integer | 请求体参数                                   | Y        |\n| uid         | user id  | Integer | 请求体参数；接收端在map中获取currentPage字段 | Y        |\n| title       | 博文标题 | Integer | 请求体参数；接收端在map中获取pageSize字段    | Y        |\n| description | 博文描述 | String  | 请求体参数                                   | Y        |\n| content     | 博文内容 | String  | 请求体参数                                   | Y        |\n| time        | 发表时间 | String  | 请求体参数                                   | N        |\n\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": null,\n    \"msg\": \"操作成功\"\n}\n```\n\n\n\n## 删除博文\n\n* 方法描述：根据bid删除博文（用户或管理员才有权限删除）\n\n* URL地址：/blog\n* 请求方式：delete\n* 请求参数：\n\n| 字段 | 说明    | 类型    | 备注     | 是否必填 |\n| ---- | ------- | ------- | -------- | -------- |\n| id   | blog id | Integer | 路径参数 | Y        |\n\n* 返回结果\n\n```json\n{\n    \"code\": 200,\n    \"data\": null,\n    \"msg\": \"操作成功\"\n}\n```\n\n', '2022-05-07 12:55:25');
INSERT INTO `blog` VALUES (38, 2, '毕业答辩', '123', '123123', '2022-05-07 23:31:48');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `cid` int(0) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stamp` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `bid` int(0) NOT NULL,
  `uid` int(0) NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `comment_blog_id`(`bid`) USING BTREE,
  INDEX `comment_user_id`(`uid`) USING BTREE,
  CONSTRAINT `comment_blog_id` FOREIGN KEY (`bid`) REFERENCES `blog` (`bid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_user_id` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, 'test111', '2022-02-23 20:15:55', 1, 2);
INSERT INTO `comment` VALUES (3, 'test111', '2022-02-23 20:15:55', 1, 2);
INSERT INTO `comment` VALUES (4, 'test111', '2022-02-23 20:15:55', 1, 2);
INSERT INTO `comment` VALUES (5, 'test111', '2022-02-23 20:15:55', 1, 2);
INSERT INTO `comment` VALUES (12, '评论博客4', '2022-03-13 20:41:25', 4, 2);
INSERT INTO `comment` VALUES (13, '2222', '2022-03-16 22:21:49', 1, 4);
INSERT INTO `comment` VALUES (19, '1234556', '2022-03-16 18:47:00', 24, 2);
INSERT INTO `comment` VALUES (22, '66666666666666', '2022-03-16 20:05:26', 24, 2);
INSERT INTO `comment` VALUES (26, 'O(∩_∩)O', '2022-05-07 12:54:19', 32, 5);
INSERT INTO `comment` VALUES (27, '12312312312', '2022-05-07 12:54:39', 22, 5);
INSERT INTO `comment` VALUES (30, '66666', '2022-05-07 23:23:18', 33, 2);

-- ----------------------------
-- Table structure for favor
-- ----------------------------
DROP TABLE IF EXISTS `favor`;
CREATE TABLE `favor`  (
  `fid` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `bid` int(0) NOT NULL,
  PRIMARY KEY (`fid`) USING BTREE,
  INDEX `like_blog_id`(`bid`) USING BTREE,
  INDEX `like_user_id`(`uid`) USING BTREE,
  CONSTRAINT `like_blog_id` FOREIGN KEY (`bid`) REFERENCES `blog` (`bid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_user_id` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favor
-- ----------------------------
INSERT INTO `favor` VALUES (4, 4, 6);
INSERT INTO `favor` VALUES (13, 2, 13);
INSERT INTO `favor` VALUES (20, 2, 24);
INSERT INTO `favor` VALUES (24, 2, 27);
INSERT INTO `favor` VALUES (25, 5, 32);
INSERT INTO `favor` VALUES (26, 5, 30);
INSERT INTO `favor` VALUES (27, 5, 22);
INSERT INTO `favor` VALUES (28, 5, 33);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` enum('admin','user') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT 'username唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123', 'admin', NULL);
INSERT INTO `user` VALUES (2, 'superman', '123', 'user', '1439068491@qq.com');
INSERT INTO `user` VALUES (4, 'ironman', '333', 'user', '1092022925@qq.com');
INSERT INTO `user` VALUES (5, 'batman', '123', 'user', '1092022925@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
