
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


-- 部门表 用于 DeptControllerV1
create table sys_dept
(
    dept_id     bigint auto_increment comment '部门id'
        primary key,
    parent_id   bigint      default 0  not null comment '父部门id',
    ancestors   text                   not null comment '祖级列表',
    dept_name   varchar(64) default '' not null comment '部门名称',
    order_num   int         default 0  not null comment '显示顺序',
    leader_id   bigint                 null,
    leader_name varchar(64)            null comment '负责人',
    phone       varchar(16)            null comment '联系电话',
    email       varchar(128)           null comment '邮箱',
    status      smallint    default 0  not null comment '部门状态（0停用 1启用）',
    creator_id  bigint                 null comment '创建者ID',
    create_time datetime               null comment '创建时间',
    updater_id  bigint                 null comment '更新者ID',
    update_time datetime               null comment '更新时间',
    deleted     tinyint(1)  default 0  not null comment '逻辑删除'
)
    comment '部门表';


-- 用于authcontroller
-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user
(
    user_id         bigint(10)   not null auto_increment comment '用户ID',
    user_name       varchar(30)  not null default '' comment '用户昵称',
    email           varchar(50)  not null default '' comment '用户邮箱',
    password        varchar(255)  not null default '' comment '密码',
    sex             tinyint(1)   not null default 2 comment '用户性别（0男 1女 2未知）',
    phone_number    varchar(11)  not null default '' comment '手机号码',
    avatar          varchar(100) not null default '' comment '头像路径',
    personal_sign   varchar(500) not null default '' comment '个性签名',
    salt            varchar(20)  not null default '' comment '盐加密',
    dept_id         bigint(4)    not null default -1 comment '部门ID',
    user_type       tinyint(1)   not null default 1 comment '用户类型（0系统用户 1普通用户）',
    status          tinyint(1)   not null default 1 comment '帐号状态（1正常 0停用）',
    login_ip        varchar(128) not null default '' comment '最后登录IP',
    login_date      datetime     not null default current_timestamp comment '最后登录时间',
    pwd_update_date datetime     not null default current_timestamp comment '密码最后更新时间',
    remark          varchar(500) not null default '' comment '备注',
    create_by       varchar(64)  not null default '' comment '创建者',
    update_by       varchar(64)  not null default '' comment '更新者',
    create_time     datetime     not null default current_timestamp comment '创建时间',
    update_time     datetime     not null default current_timestamp on update current_timestamp comment '更新时间',
    del_flag        tinyint(1)   not null default 0 comment '删除标志（0代表存在 1代表删除）',
    primary key (user_id),
    unique key uk_email (email)
) engine = innodb
  default charset = utf8mb4
  auto_increment = 10000 comment = '用户信息表';


-- 用于NoticeV3controller
-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
                           `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                           `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
                           `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
                           `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
                           `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告信息表' ROW_FORMAT = Dynamic;



-- 用于musiccontroller
-- 导出  表 open_music.music_info 结构
CREATE TABLE IF NOT EXISTS `music_info` (
                                            `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '音乐表主键',
                                            `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '音乐名称标题（音乐名称）',
                                            `artist` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者',
                                            `album` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专辑',
                                            `sort` int DEFAULT '1' COMMENT '排序',
                                            `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0-已启用，1-已停用，2-已删除',
                                            `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                            `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                            `link_status` int unsigned NOT NULL DEFAULT '0' COMMENT '链接填充状态：0-未填充链接，1-已填充链接',
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='音乐表';

-- 数据导出被取消选择。

-- 导出  表 open_music.music_info_link 结构
CREATE TABLE IF NOT EXISTS `music_info_link` (
                                                 `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                 `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0-已启用，1-已停用，2-已删除',
                                                 `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                 `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                                 `link_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '音频链接',
                                                 `link_type` int NOT NULL DEFAULT '0' COMMENT '链接类型：0-音频，1-封面',
                                                 `link_source` int NOT NULL DEFAULT '0' COMMENT '链接来源：0-github，1-alist',
                                                 `music_id` bigint unsigned NOT NULL COMMENT 'music表id',
                                                 PRIMARY KEY (`id`),
                                                 KEY `music_id` (`music_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='音乐链接信息表';


-- 公司表 用于companyV1
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`
(
    `id`           varchar(64)  NOT NULL COMMENT '公司id',
    `company_name` varchar(30)  NULL DEFAULT '' COMMENT '公司名称',
    `order_num`    int(0)                                                       NULL DEFAULT 0 COMMENT '显示顺序',
    `status`       char(1)      NULL DEFAULT '0' COMMENT '公司状态（0正常 1停用）',
    `del_flag`     char(1)      NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0)                                                  NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0)                                                  NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '部门表'  ROW_FORMAT = Compact;

-- 菜单表 用于menuv2
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `id` varchar(64) NOT NULL COMMENT 'PK',
                             `parent_id` varchar(64) NOT NULL COMMENT '父菜单ID',
                             `tree_path` varchar(255)  NULL DEFAULT NULL COMMENT '父节点ID路径',
                             `name` varchar(64)  NOT NULL DEFAULT '' COMMENT '菜单名称',
                             `menu_type` int NOT NULL COMMENT '菜单类型(1:菜单；2:目录；3:外链；4:按钮)',
                             `path` varchar(128)  NULL DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
                             `component` varchar(128)  NULL DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
                             `perm` varchar(128)  NULL DEFAULT NULL COMMENT '权限标识',
                             `visible` int NOT NULL DEFAULT 1 COMMENT '显示状态(1-显示;0-隐藏)',
                             `menu_sort` int NULL DEFAULT 0 COMMENT '排序',
                             `icon` varchar(64)  NULL DEFAULT '' COMMENT '菜单图标',
                             `redirect` varchar(128)  NULL DEFAULT NULL COMMENT '跳转路径',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
                             `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
                             `del_flag` int NOT NULL COMMENT '逻辑删除字段',
                             `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                             `ancestors` varchar(600)  NULL DEFAULT NULL COMMENT '祖级id路径(parentid/sonid/id)',
                             `query_params`  varchar(255)  NULL DEFAULT NULL COMMENT '路由参数',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- 关注表 用于follow
create table forest_follow
(
    id             bigint auto_increment comment '主键'
        primary key,
    follower_id    bigint null comment '关注者 id',
    following_id   bigint null comment '关注数据 id',
    following_type char   null comment '0：用户，1：标签，2：帖子收藏，3：帖子关注'
) comment '关注表 ' collate = utf8mb4_unicode_ci;


-- 用于novel
-- ----------------------------
-- Table structure for author_info
-- ----------------------------
DROP TABLE IF EXISTS `author_info`;
CREATE TABLE `author_info` (
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
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                 `work_direction` tinyint(3) unsigned NOT NULL COMMENT '作品方向;0-男频 1-女频',
                                 `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名',
                                 `sort` tinyint(3) unsigned NOT NULL DEFAULT '10' COMMENT '排序',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE KEY `pk_id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小说类别';

-- ----------------------------
-- Table structure for book_chapter
-- ----------------------------
DROP TABLE IF EXISTS `book_chapter`;
CREATE TABLE `book_chapter` (
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
-- Table structure for book_comment
-- ----------------------------
DROP TABLE IF EXISTS `book_comment`;
CREATE TABLE `book_comment` (
                                `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `book_id` bigint(20) unsigned NOT NULL COMMENT '评论小说ID',
                                `user_id` bigint(20) unsigned NOT NULL COMMENT '评论用户ID',
                                `comment_content` varchar(512) NOT NULL COMMENT '评价内容',
                                `reply_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '回复数量',
                                `audit_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '审核状态;0-待审核 1-审核通过 2-审核不通过',
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_bookId_userId` (`book_id`,`user_id`),
                                UNIQUE KEY `pk_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小说评论';

-- ----------------------------
-- Table structure for book_content
-- ----------------------------
DROP TABLE IF EXISTS `book_content`;
CREATE TABLE `book_content` (
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
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
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


-- 用于commentv2
--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论主id',
                           `vid` int(11) NOT NULL COMMENT '评论的视频id',
                           `uid` int(11) DEFAULT NULL COMMENT '发送者id',
                           `root_id` int(11) NOT NULL DEFAULT '0' COMMENT '根节点评论的id,如果为0表示为根节点',
                           `parent_id` int(11) NOT NULL COMMENT '被回复的评论id，只有root_id为0时才允许为0，表示根评论',
                           `to_user_id` int(11) NOT NULL COMMENT '回复目标用户id',
                           `content` varchar(2000) NOT NULL COMMENT '评论内容',
                           `love` int(11) NOT NULL DEFAULT '0' COMMENT '该条评论的点赞数',
                           `bad` int(11) DEFAULT '0' COMMENT '不喜欢的数量',
                           `create_time` datetime NOT NULL COMMENT '创建时间',
                           `is_top` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否置顶 0普通 1置顶',
                           `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '软删除 0未删除 1已删除',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;
