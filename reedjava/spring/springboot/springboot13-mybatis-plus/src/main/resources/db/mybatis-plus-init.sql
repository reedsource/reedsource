-- 项目数据库初始化
-- 删除数据库
DROP
    DATABASE IF EXISTS reedsource;
-- 创建数据库
create schema reedsource collate utf8mb4_bin;
-- 切换到数据库
USE
    reedsource;


DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id    BIGINT      NOT NULL COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age   INT         NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

truncate table user;

INSERT INTO user (id, name, age, email)
VALUES (1, '张三', 18, 'test1@baomidou.com'),
       (2, '李四', 20, 'test2@baomidou.com'),
       (3, '王五', 28, 'test3@baomidou.com'),
       (4, '赵六', 21, 'test4@baomidou.com'),
       (5, '陈六', 24, 'test5@baomidou.com');