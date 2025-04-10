CREATE TABLE dynamic_qr (
                            id VARCHAR(50) PRIMARY KEY,       -- 唯一短码（二维码内容中包含的部分）
                            type VARCHAR(20),                 -- 类型：text、url、html、image
                            content TEXT,                     -- 存储实际内容或地址
                            create_time DATETIME,
                            update_time DATETIME
);
