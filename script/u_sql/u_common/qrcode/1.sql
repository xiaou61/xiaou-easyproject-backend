-- qrcodelogin登录信息表
CREATE TABLE u_qrlogin (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
                           uuid VARCHAR(64) NOT NULL UNIQUE COMMENT '唯一标识',
                           device VARCHAR(64) COMMENT '设备号',
                           token TEXT COMMENT 'JWT令牌',
                           status VARCHAR(32) COMMENT '扫码状态',
                           create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);
