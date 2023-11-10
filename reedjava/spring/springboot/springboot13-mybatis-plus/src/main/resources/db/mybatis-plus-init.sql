-- 项目数据库初始化
-- 删除数据库
DROP
    DATABASE IF EXISTS reedsource;
-- 创建数据库
create schema reedsource collate utf8mb4_bin;
-- 切换到数据库
USE
    reedsource;


-- 姓名表 ------------------------------------------------------------------------------

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id    BIGINT      NOT NULL COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age   INT         NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    types BIGINT      NULL DEFAULT NULL COMMENT '类型ID',
    PRIMARY KEY (id)
);

truncate table user;

INSERT INTO user (id, name, age, email, types)
VALUES (1, '张三', 33, 'test111111@q.com', 1),
       (2, '李四', 44, 'test222223@q.com', 3),
       (3, '王五', 55, 'test333333@q.com', 5),
       (4, '赵六', 66, 'test444444@q.com', 7),
       (5, '陈六', 77, 'test555555@q.com', 9);

-- 类型表  实际为三级或更多级别案例实现 ------------------------------------------------------------------------------


DROP TABLE IF EXISTS types;

CREATE TABLE types
(
    id    BIGINT      NOT NULL COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '名称',
    level INT         NULL DEFAULT NULL COMMENT '级别',
    tid   BIGINT      NULL DEFAULT NULL COMMENT '父级id',
    PRIMARY KEY (id)
);

truncate table types;

INSERT INTO types (id, name, level, tid)
VALUES (1, '1', 1, 0),
       (2, '1-1', 2, 1),
       (3, '1-2', 2, 1),
       (4, '2', 1, 0),
       (5, '1-1-1', 3, 2),
       (6, '2-1', 2, 4),
       (7, '2-2', 2, 4),
       (8, '2-1-1', 2, 6),
       (9, '2-1-2', 2, 6);