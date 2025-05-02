-- 删除已存在的数据库（使用反引号包裹带中划线的名字）
DROP DATABASE IF EXISTS `sx-easy`;

-- 创建新的数据库
CREATE DATABASE `sx-easy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用该数据库
USE `sx-easy`;

-- 删除已存在的 sys_user 表
DROP TABLE IF EXISTS sys_user;

-- 创建 sys_user 用户表
CREATE TABLE sys_user
(
    id          INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    name        VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    password    VARCHAR(100) NOT NULL COMMENT '用户密码'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
INSERT INTO sys_user (id,name, password)
VALUES (1,'xiaou', 'xiaou');





# xiaou-music板块
-- 如果 singer 表已存在，则先删除，避免冲突
DROP TABLE IF EXISTS `u_singer`;

-- 设置字符集为 utf8，避免插入中文时出现乱码
SET character_set_client = utf8;

-- 创建 singer 表，保存歌手信息
CREATE TABLE `u_singer` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  -- 主键，自增ID
                          `name` varchar(45) NOT NULL,                    -- 歌手名称，不能为空
                          `sex` tinyint(4) DEFAULT NULL,                  -- 性别（0-女，1-男，或其他）
                          `pic` varchar(255) DEFAULT NULL,                -- 歌手图片路径或链接
                          `birth` datetime DEFAULT NULL,                  -- 出生日期时间
                          `location` varchar(45) DEFAULT NULL,            -- 所在地信息
                          `introduction` varchar(255) DEFAULT NULL,       -- 歌手简介
                          PRIMARY KEY (`id`)                              -- 主键约束
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;


-- 如果存在名为 `song` 的表，则将其删除
DROP TABLE IF EXISTS `u_song`;

-- 创建 `song` 表
CREATE TABLE `u_song` (
                        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,           -- 歌曲ID，自增主键
                        `singer_id` int(10) unsigned NOT NULL,                   -- 歌手ID，外键
                        `name` varchar(45) NOT NULL,                             -- 歌曲名称
                        `introduction` varchar(255) DEFAULT NULL,                -- 歌曲简介
                        `create_time` datetime NOT NULL COMMENT '发行时间',      -- 歌曲发行时间
                        `update_time` datetime NOT NULL,                         -- 最后更新时间
                        `pic` varchar(255) DEFAULT NULL,                         -- 歌曲封面图片路径
                        `lyric` text,                                            -- 歌词内容
                        `url` varchar(255) NOT NULL,                             -- 歌曲播放路径
                        PRIMARY KEY (`id`)                                       -- 主键设置为 `id`
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

#xiaou-novel模块

DROP TABLE IF EXISTS `u_author_info`;
CREATE TABLE `u_author_info` (
                               `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
                               `invite_code` varchar(20) NOT NULL COMMENT '邀请码',
                               `pen_name` varchar(20) NOT NULL COMMENT '笔名',
                               `tel_phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
                               `chat_account` varchar(50) DEFAULT NULL COMMENT 'QQ或微信账号',
                               `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
                               `work_direction` tinyint(3) unsigned DEFAULT NULL COMMENT '作品方向;0-男频 1-女频',
                               `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0：正常;1-封禁',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_userId` (`user_id`),
                               UNIQUE KEY `pk_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作者信息';


-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `u_book_info`;
CREATE TABLE `u_book_info` (
                             `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `work_direction` tinyint(3) unsigned DEFAULT NULL COMMENT '作品方向;0-男频 1-女频',
                             `category_id` bigint(20) unsigned DEFAULT NULL COMMENT '类别ID',
                             `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类别名',
                             `pic_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小说封面地址',
                             `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小说名',
                             `author_id` bigint(20) unsigned NOT NULL COMMENT '作家id',
                             `author_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作家名',
                             `book_desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书籍描述',
                             `score` tinyint(3) unsigned NOT NULL COMMENT '评分;总分:10 ，真实评分 = score/10',
                             `book_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '书籍状态;0-连载中 1-已完结',
                             `visit_count` bigint(20) unsigned NOT NULL DEFAULT '103' COMMENT '点击量',
                             `word_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总字数',
                             `comment_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论数',
                             `last_chapter_id` bigint(20) unsigned DEFAULT NULL COMMENT '最新章节ID',
                             `last_chapter_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最新章节名',
                             `last_chapter_update_time` datetime DEFAULT NULL COMMENT '最新章节更新时间',
                             `is_vip` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否收费;1-收费 0-免费',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `uk_bookName_authorName` (`book_name`,`author_name`) USING BTREE,
                             UNIQUE KEY `pk_id` (`id`) USING BTREE,
                             KEY `idx_createTime` (`create_time`) USING BTREE,
                             KEY `idx_lastChapterUpdateTime` (`last_chapter_update_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1431630596354977793 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小说信息';


-- ----------------------------
-- Table structure for book_chapter
-- ----------------------------
DROP TABLE IF EXISTS `u_book_chapter`;
CREATE TABLE `u_book_chapter` (
                                `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                `book_id` bigint(20) unsigned NOT NULL COMMENT '小说ID',
                                `chapter_num` smallint(5) unsigned NOT NULL COMMENT '章节号',
                                `chapter_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节名',
                                `word_count` int(10) unsigned NOT NULL COMMENT '章节字数',
                                `is_vip` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否收费;1-收费 0-免费',
                                `create_time` datetime DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE KEY `uk_bookId_chapterNum` (`book_id`,`chapter_num`) USING BTREE,
                                UNIQUE KEY `pk_id` (`id`) USING BTREE,
                                KEY `idx_bookId` (`book_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1445988184596992001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小说章节';


-- ----------------------------
-- Table structure for book_content
-- ----------------------------
DROP TABLE IF EXISTS `u_book_content`;
CREATE TABLE `u_book_content` (
                                `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `chapter_id` bigint(20) unsigned NOT NULL COMMENT '章节ID',
                                `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小说章节内容',
                                `create_time` datetime DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE KEY `uk_chapterId` (`chapter_id`) USING BTREE,
                                UNIQUE KEY `pk_id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4256332 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小说内容';


-- ----------------------------
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `u_book_category`;
CREATE TABLE `u_book_category` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                 `work_direction` tinyint(3) unsigned NOT NULL COMMENT '作品方向;0-男频 1-女频',
                                 `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名',
                                 `sort` tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '排序',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE KEY `pk_id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小说类别';

#xiaou-online-exam模块
DROP TABLE IF EXISTS `u_exam_category`;
CREATE TABLE `u_exam_category` (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
                              `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '分类名称',
                              `parent_id` int(11) DEFAULT 0 COMMENT '父分类ID，0表示一级分类',
                              `sort` int(11) DEFAULT 0 COMMENT '排序',
                              `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                              `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除：0代表未删除，1代表删除',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `u_exam_question`;
CREATE TABLE `u_exam_question` (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id   试题表',
                              `qu_type` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '试题类型',
                              `image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '试题图片',
                              `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '题干',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `analysis` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '题目分析',
                              `repo_id` int(11) DEFAULT NULL COMMENT '题库id',
                              `user_id` int(11) DEFAULT NULL COMMENT '用户id',
                              `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除：0代表未删除，1代表删除',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=718 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `u_exam_option`;
CREATE TABLE `u_exam_option` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id   选项答案表',
                            `qu_id` int(11) NOT NULL COMMENT '试题id',
                            `is_right` int(11) DEFAULT NULL COMMENT '是否正确',
                            `image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片地址   0错误 1正确',
                            `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '选项内容',
                            `sort` int(11) DEFAULT NULL COMMENT '排序',
                            `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除：0代表未删除，1代表删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2642 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `u_exam_repo`;
CREATE TABLE `u_exam_repo` (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id   题库表',
                          `user_id` int(11) NOT NULL COMMENT '创建人id',
                          `title` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '题库标题',
                          `category_id` int(11) DEFAULT NULL COMMENT '分类ID',
                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除：0代表未删除，1代表删除',
                          `is_exercise` int(11) NOT NULL DEFAULT '0',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;


#xiaou-hot模块
-- 热点表
create table if not exists u_hot_post
(
    id             bigint auto_increment comment 'id' primary key,
    name           varchar(256)                            null comment '排行榜名称',
    type           varchar(256)                            null comment ' 热点类型',
    typeName       varchar(256)                            null comment ' 热点类型名称',
    iconUrl        varchar(512)                            null comment '图标地址',
    hostJson       mediumtext                              null comment '热点数据（json）',
    category       int                                     null comment '分类',
    updateInterval decimal(7, 2) default 0.50              null comment '更新间隔，以小时为单位',
    sort           int           default 0                 not null comment ' 排序',
    createTime     datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete       tinyint       default 0                 not null comment '是否删除',
    index idx_postId (sort)
) comment '热点表' collate = utf8mb4_unicode_ci;
