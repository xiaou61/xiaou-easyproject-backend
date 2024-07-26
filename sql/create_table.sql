
-- 创建库
create database if not exists xiaou_easy_project;

-- 切换库
use xiaou_easy_project;
-- 用户表 LoginControllerV1所用到的
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    user_account  varchar(256)                           not null comment '账号',
    user_password varchar(512)                           not null comment '密码',
    user_name     varchar(256)                           null comment '用户昵称',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除',
    index idx_union_id (user_account)
) comment '用户' collate = utf8mb4_unicode_ci;


-- 试题表 QuestionControllerV1所用到
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
                              `id` int NOT NULL AUTO_INCREMENT COMMENT 'id   试题表',
                              `qu_type` varchar(255) NOT NULL COMMENT '试题类型',
                              `image` varchar(255) DEFAULT NULL COMMENT '试题图片',
                              `content` varchar(255) NOT NULL COMMENT '题干',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `analysis` varchar(255) DEFAULT NULL COMMENT '题目分析',
                              `repo_id` int NOT NULL COMMENT '题库id',
                              `user_id` int DEFAULT NULL COMMENT '用户id',
                              `is_deleted` int NOT NULL DEFAULT '0' COMMENT '逻辑删除：0代表未删除，1代表删除',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=702 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_option
-- ----------------------------
DROP TABLE IF EXISTS `t_option`;
CREATE TABLE `t_option` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT 'id   选项答案表',
                            `qu_id` int NOT NULL COMMENT '试题id',
                            `is_right` int DEFAULT NULL COMMENT '是否正确',
                            `image` varchar(255) DEFAULT NULL COMMENT '图片地址   0错误 1正确',
                            `content` varchar(255) NOT NULL COMMENT '选项内容',
                            `sort` int DEFAULT NULL COMMENT '排序',
                            `is_deleted` int NOT NULL DEFAULT '0' COMMENT '逻辑删除：0代表未删除，1代表删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2608 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
-- ----------------------------
-- Table structure for t_repo
-- ----------------------------
DROP TABLE IF EXISTS `t_repo`;
CREATE TABLE `t_repo` (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT 'id   题库表',
                          `user_id` int NOT NULL COMMENT '创建人id',
                          `title` varchar(255) NOT NULL COMMENT '题库标题',
                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `is_deleted` int NOT NULL DEFAULT '0' COMMENT '逻辑删除：0代表未删除，1代表删除',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

