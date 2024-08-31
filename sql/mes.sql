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