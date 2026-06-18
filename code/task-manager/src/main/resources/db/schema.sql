-- 任务管理系统 建表 SQL（第 27 章）
-- 执行：mysql -u root -p < schema.sql，或在客户端里粘贴运行

CREATE DATABASE IF NOT EXISTS task_manager DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
USE task_manager;

-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(20)  NOT NULL UNIQUE COMMENT '用户名',
    password    VARCHAR(100) NOT NULL        COMMENT 'BCrypt 哈希',
    create_time DATETIME                      COMMENT '创建时间',
    INDEX idx_username (username)
) COMMENT '用户表';

-- 任务表
DROP TABLE IF EXISTS t_task;
CREATE TABLE t_task (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(100) NOT NULL        COMMENT '标题',
    description VARCHAR(500)                 COMMENT '描述',
    status      TINYINT      DEFAULT 0       COMMENT '0待办 1进行中 2已完成',
    user_id     BIGINT                        COMMENT '归属用户 ID（对应 t_user.id）',
    create_time DATETIME,
    update_time DATETIME,
    deleted     TINYINT      DEFAULT 0       COMMENT '逻辑删除 0正常 1删除',
    INDEX idx_user_status (user_id, status)
) COMMENT '任务表';
