-- ----------------------------
-- Table structure for cal_holiday
-- ----------------------------
DROP TABLE IF EXISTS `cal_holiday`;
CREATE TABLE `cal_holiday` (
                               `holiday_id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
                               `the_day` datetime DEFAULT NULL COMMENT '日期',
                               `holiday_type` varchar(64) DEFAULT NULL COMMENT '日期类型',
                               `start_time` datetime DEFAULT NULL COMMENT '开始时间',
                               `end_time` datetime DEFAULT NULL COMMENT '结束时间',
                               `remark` varchar(500) DEFAULT '' COMMENT '备注',
                               `attr1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
                               `attr2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
                               `attr3` int DEFAULT '0' COMMENT '预留字段3',
                               `attr4` int DEFAULT '0' COMMENT '预留字段4',
                               `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (`holiday_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='节假日设置';
-- cal_holiday
ALTER TABLE cal_holiday
    ADD COLUMN deleted INT DEFAULT 0 COMMENT '逻辑删除字段 0:未删除 1:已删除',
    ADD COLUMN deleteAt DATETIME DEFAULT '1000-01-01 00:00:00' COMMENT '逻辑删除辅助字段',
    ADD COLUMN version INT DEFAULT 1 COMMENT '乐观锁';


-- ----------------------------
-- Table structure for cal_team_member
-- ----------------------------
DROP TABLE IF EXISTS `cal_team_member`;
CREATE TABLE `cal_team_member` (
                                   `member_id` bigint NOT NULL AUTO_INCREMENT COMMENT '班组成员ID',
                                   `team_id` bigint NOT NULL COMMENT '班组ID',
                                   `user_id` bigint NOT NULL COMMENT '用户ID',
                                   `user_name` varchar(64) NOT NULL COMMENT '用户名',
                                   `nick_name` varchar(64) DEFAULT NULL COMMENT '用户昵称',
                                   `tel` varchar(64) DEFAULT NULL COMMENT '电话',
                                   `remark` varchar(500) DEFAULT '' COMMENT '备注',
                                   `attr1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
                                   `attr2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
                                   `attr3` int DEFAULT '0' COMMENT '预留字段3',
                                   `attr4` int DEFAULT '0' COMMENT '预留字段4',
                                   `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                   PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班组成员表';



-- ----------------------------
-- Table structure for cal_team
-- ----------------------------
DROP TABLE IF EXISTS `cal_team`;
CREATE TABLE `cal_team` (
                            `team_id` bigint NOT NULL AUTO_INCREMENT COMMENT '班组ID',
                            `team_code` varchar(64) NOT NULL COMMENT '班组编号',
                            `team_name` varchar(255) NOT NULL COMMENT '班组名称',
                            `calendar_type` varchar(64) DEFAULT NULL COMMENT '班组类型',
                            `remark` varchar(500) DEFAULT '' COMMENT '备注',
                            `attr1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
                            `attr2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
                            `attr3` int DEFAULT '0' COMMENT '预留字段3',
                            `attr4` int DEFAULT '0' COMMENT '预留字段4',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班组表';


-- ----------------------------
-- Table structure for cal_plan
-- ----------------------------
DROP TABLE IF EXISTS `cal_plan`;
CREATE TABLE `cal_plan` (
                            `plan_id` bigint NOT NULL AUTO_INCREMENT COMMENT '计划ID',
                            `plan_code` varchar(64) NOT NULL COMMENT '计划编号',
                            `plan_name` varchar(255) NOT NULL COMMENT '计划名称',
                            `calendar_type` varchar(64) DEFAULT NULL COMMENT '班组类型',
                            `start_date` datetime NOT NULL COMMENT '开始日期',
                            `end_date` datetime NOT NULL COMMENT '结束日期',
                            `shift_type` varchar(64) DEFAULT NULL COMMENT '轮班方式',
                            `shift_method` varchar(64) DEFAULT NULL COMMENT '倒班方式',
                            `shift_count` int DEFAULT NULL COMMENT '数',
                            `status` varchar(64) DEFAULT 'PREPARE' COMMENT '状态',
                            `remark` varchar(500) DEFAULT '' COMMENT '备注',
                            `attr1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
                            `attr2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
                            `attr3` int DEFAULT '0' COMMENT '预留字段3',
                            `attr4` int DEFAULT '0' COMMENT '预留字段4',
                            `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='排班计划表';



-- ----------------------------
-- Table structure for cal_teamshift
-- ----------------------------
DROP TABLE IF EXISTS `cal_teamshift`;
CREATE TABLE `cal_teamshift` (
                                 `record_id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
                                 `the_day` varchar(64) NOT NULL COMMENT '日期',
                                 `team_id` bigint NOT NULL COMMENT '班组ID',
                                 `team_name` varchar(255) DEFAULT NULL COMMENT '班组名称',
                                 `shift_id` bigint NOT NULL COMMENT '班次ID',
                                 `shift_name` varchar(255) DEFAULT NULL COMMENT '班次名称',
                                 `order_num` int DEFAULT NULL COMMENT '序号',
                                 `plan_id` bigint DEFAULT NULL COMMENT '计划ID',
                                 `calendar_type` varchar(64) DEFAULT NULL COMMENT '班组类型',
                                 `shift_type` varchar(64) DEFAULT NULL COMMENT '轮班方式',
                                 `remark` varchar(500) DEFAULT '' COMMENT '备注',
                                 `attr1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
                                 `attr2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
                                 `attr3` int DEFAULT '0' COMMENT '预留字段3',
                                 `attr4` int DEFAULT '0' COMMENT '预留字段4',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=745 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班组排班表';


-- ----------------------------
-- Table structure for cal_plan_team
-- ----------------------------
DROP TABLE IF EXISTS `cal_plan_team`;
CREATE TABLE `cal_plan_team` (
                                 `record_id` bigint NOT NULL AUTO_INCREMENT COMMENT '流水号',
                                 `plan_id` bigint NOT NULL COMMENT '计划ID',
                                 `team_id` bigint NOT NULL COMMENT '班组ID',
                                 `team_code` varchar(64) DEFAULT NULL COMMENT '班组编号',
                                 `team_name` varchar(64) DEFAULT NULL COMMENT '班组名称',
                                 `remark` varchar(500) DEFAULT '' COMMENT '备注',
                                 `attr1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
                                 `attr2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
                                 `attr3` int DEFAULT '0' COMMENT '预留字段3',
                                 `attr4` int DEFAULT '0' COMMENT '预留字段4',
                                 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='计划班组表';



-- ----------------------------
-- Table structure for cal_shift
-- ----------------------------
DROP TABLE IF EXISTS `cal_shift`;
CREATE TABLE `cal_shift` (
                             `shift_id` bigint NOT NULL AUTO_INCREMENT COMMENT '班次ID',
                             `plan_id` bigint NOT NULL COMMENT '计划ID',
                             `order_num` int NOT NULL COMMENT '序号',
                             `shift_name` varchar(64) NOT NULL COMMENT '班次名称',
                             `start_time` varchar(10) NOT NULL COMMENT '开始时间',
                             `end_time` varchar(10) NOT NULL COMMENT '结束时间',
                             `remark` varchar(500) DEFAULT '' COMMENT '备注',
                             `attr1` varchar(64) DEFAULT NULL COMMENT '预留字段1',
                             `attr2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
                             `attr3` int DEFAULT '0' COMMENT '预留字段3',
                             `attr4` int DEFAULT '0' COMMENT '预留字段4',
                             `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`shift_id`)
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='计划班次表';
