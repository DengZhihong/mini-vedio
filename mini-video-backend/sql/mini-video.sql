/*
Navicat MySQL Data Transfer

Source Server         : 阿里云47.107.50.179
Source Server Version : 80018
Source Host           : 47.107.50.179:3306
Source Database       : mini-video

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-11-17 17:34:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bgm
-- ----------------------------
DROP TABLE IF EXISTS `bgm`;
CREATE TABLE `bgm` (
  `id` varchar(64) NOT NULL,
  `author` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bgm
-- ----------------------------

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` varchar(64) NOT NULL,
  `video_id` int(20) NOT NULL,
  `from_user_id` int(20) NOT NULL,
  `comment` text NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for search_records
-- ----------------------------
DROP TABLE IF EXISTS `search_records`;
CREATE TABLE `search_records` (
  `id` varchar(64) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of search_records
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(64) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `face_image` varchar(255) NOT NULL COMMENT '用户头像',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `fans_counts` int(11) NOT NULL DEFAULT '0' COMMENT '粉丝数量',
  `follow_counts` int(11) NOT NULL DEFAULT '0' COMMENT '关注数',
  `receive_like_counts` int(11) NOT NULL DEFAULT '0' COMMENT '收到的喜欢数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- Table structure for users_fans
-- ----------------------------
DROP TABLE IF EXISTS `users_fans`;
CREATE TABLE `users_fans` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `fan_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`fan_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_fans
-- ----------------------------

-- ----------------------------
-- Table structure for users_like_videos
-- ----------------------------
DROP TABLE IF EXISTS `users_like_videos`;
CREATE TABLE `users_like_videos` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `video_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_video_index` (`user_id`,`video_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_like_videos
-- ----------------------------

-- ----------------------------
-- Table structure for users_report
-- ----------------------------
DROP TABLE IF EXISTS `users_report`;
CREATE TABLE `users_report` (
  `id` int(11) NOT NULL,
  `deal_user_id` varchar(64) NOT NULL COMMENT '被举报人id',
  `deal_video_id` varchar(64) NOT NULL COMMENT '被举报视频id',
  `title` varchar(64) NOT NULL COMMENT '举报标题',
  `content` varchar(255) DEFAULT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '举报人id',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_report
-- ----------------------------

-- ----------------------------
-- Table structure for videos
-- ----------------------------
DROP TABLE IF EXISTS `videos`;
CREATE TABLE `videos` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '视频发布作者',
  `audio_id` varchar(64) DEFAULT NULL COMMENT '音频的id，配乐',
  `video_desc` varchar(128) DEFAULT NULL,
  `video_path` varchar(255) NOT NULL,
  `video_seconds` float(6,0) DEFAULT NULL,
  `video_width` int(6) DEFAULT NULL,
  `video_height` int(6) DEFAULT NULL,
  `cover_path` varchar(255) NOT NULL COMMENT '视频封面',
  `like_counts` bigint(20) NOT NULL COMMENT '视频被喜欢数',
  `status` int(1) NOT NULL COMMENT '视频状态:1,发布成功;2,禁止播放; 管理员操作',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of videos
-- ----------------------------
