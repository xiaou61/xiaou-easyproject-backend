
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;


-- 定时任务 用于JobControllerV1
-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
                          `id` int NOT NULL AUTO_INCREMENT COMMENT '任务ID',
                          `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
                          `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
                          `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
                          `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
                          `misfire_policy` tinyint(1) NULL DEFAULT 3 COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
                          `concurrent` tinyint(1) NULL DEFAULT 1 COMMENT '是否并发执行（0禁止 1允许）',
                          `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态（0暂停 1正常）',
                          `create_time` datetime NOT NULL COMMENT '创建时间',
                          `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                          `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
                          PRIMARY KEY (`id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_job_log`;
CREATE TABLE `t_job_log`  (
                              `id` int NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
                              `job_id` int NOT NULL COMMENT '任务ID',
                              `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
                              `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
                              `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
                              `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
                              `status` tinyint(1) NULL DEFAULT 0 COMMENT '执行状态（0正常 1失败）',
                              `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
                              `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                              `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
                              `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = DYNAMIC;


-- 用于commentcontrollerv1
-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
                              `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `user_id` int NOT NULL COMMENT '评论用户Id',
                              `topic_id` int NULL DEFAULT NULL COMMENT '评论主题id',
                              `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
                              `reply_user_id` int NULL DEFAULT NULL COMMENT '回复用户id',
                              `parent_id` int NULL DEFAULT NULL COMMENT '父评论id',
                              `type` tinyint NOT NULL COMMENT '评论类型 1.文章 2.留言 3.关于我 4.友链 5.说说',
                              `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除  0否 1是',
                              `is_review` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否审核',
                              `create_time` datetime NOT NULL COMMENT '评论时间',
                              `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `fk_comment_user`(`user_id` ASC) USING BTREE,
                              INDEX `fk_comment_parent`(`parent_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
                                `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                                `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱号',
                                `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
                                `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户头像',
                                `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户简介',
                                `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人网站',
                                `is_subscribe` tinyint(1) NULL DEFAULT NULL COMMENT '是否订阅',
                                `is_disable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用',
                                `create_time` datetime NOT NULL COMMENT '创建时间',
                                `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;


-- 广告表 用于AdvertiseControllerV1
-- ----------------------------
-- Table structure for sms_home_advertise
-- ----------------------------
DROP TABLE IF EXISTS `sms_home_advertise`;
CREATE TABLE `sms_home_advertise`  (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                       `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `type` int(1) NULL DEFAULT NULL COMMENT '轮播位置：0->PC首页轮播；1->app首页轮播',
                                       `pic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                       `start_time` datetime NULL DEFAULT NULL,
                                       `end_time` datetime NULL DEFAULT NULL,
                                       `status` int(1) NULL DEFAULT NULL COMMENT '上下线状态：0->下线；1->上线',
                                       `click_count` int(11) NULL DEFAULT NULL COMMENT '点击数',
                                       `order_count` int(11) NULL DEFAULT NULL COMMENT '下单数',
                                       `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
                                       `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                                       `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '首页轮播广告表' ROW_FORMAT = DYNAMIC;



-- 菜单表 用于 MenuControllerV1
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`  (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级ID',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
                             `level` int(4) NULL DEFAULT NULL COMMENT '菜单级数',
                             `sort` int(4) NULL DEFAULT NULL COMMENT '菜单排序',
                             `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端名称',
                             `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端图标',
                             `hidden` int(1) NULL DEFAULT NULL COMMENT '前端隐藏',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台菜单表' ROW_FORMAT = DYNAMIC;


-- 公告表 用于NoticeControllerV1
CREATE TABLE `tz_notice` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公告id',
                             `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
                             `title` varchar(36) DEFAULT NULL COMMENT '公告标题',
                             `content` text COMMENT '公告内容',
                             `status` tinyint(1) DEFAULT NULL COMMENT '状态(1:公布 0:撤回)',
                             `is_top` tinyint(2) DEFAULT NULL COMMENT '是否置顶',
                             `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
                             `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- 评论表 用于 CommentControllerV2
CREATE TABLE `tz_prod_comm` (
                                `prod_comm_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `prod_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
                                `order_item_id` bigint(20) unsigned DEFAULT NULL COMMENT '订单项ID',
                                `user_id` varchar(36) DEFAULT NULL COMMENT '评论用户ID',
                                `content` varchar(500) DEFAULT '' COMMENT '评论内容',
                                `reply_content` varchar(500) DEFAULT '' COMMENT '掌柜回复',
                                `rec_time` datetime DEFAULT NULL COMMENT '记录时间',
                                `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
                                `reply_sts` int(1) DEFAULT '0' COMMENT '是否回复 0:未回复  1:已回复',
                                `postip` varchar(16) DEFAULT NULL COMMENT 'IP来源',
                                `score` tinyint(2) DEFAULT '0' COMMENT '得分，0-5分',
                                `useful_counts` int(11) DEFAULT '0' COMMENT '有用的计数',
                                `pics` varchar(1000) DEFAULT NULL COMMENT '晒图的json字符串',
                                `is_anonymous` int(1) DEFAULT '0' COMMENT '是否匿名(1:是  0:否)',
                                `status` int(1) DEFAULT NULL COMMENT '是否显示，1:为显示，0:待审核， -1：不通过审核，不显示。 如果需要审核评论，则是0,，否则1',
                                `evaluate` tinyint(2) DEFAULT NULL COMMENT '评价(0好评 1中评 2差评)',
                                PRIMARY KEY (`prod_comm_id`),
                                KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评论';


-- notice表 用于 NoticeControllerV2
create table sys_notice
(
    notice_id      int auto_increment comment '公告ID'
        primary key,
    notice_title   varchar(64)             not null comment '公告标题',
    notice_type    smallint                not null comment '公告类型（1通知 2公告）',
    notice_content text                    null comment '公告内容',
    status         smallint     default 0  not null comment '公告状态（1正常 0关闭）',
    creator_id     bigint                  not null comment '创建者ID',
    create_time    datetime                null comment '创建时间',
    updater_id     bigint                  null comment '更新者ID',
    update_time    datetime                null comment '更新时间',
    remark         varchar(255) default '' not null comment '备注',
    deleted        tinyint(1)   default 0  not null comment '逻辑删除'
)
    comment '通知公告表';
