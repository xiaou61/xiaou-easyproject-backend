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
