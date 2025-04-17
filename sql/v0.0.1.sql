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


