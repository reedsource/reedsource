-- 2.唯一约束.唯一约束可以作用在单列字段上面,也可以同时作用在多列字段上面,所以这是一个表级约束
-- 唯一约束保障数据或数据的组合不能重复,但是可以为NULL
-- 在一个表中可以同时有多个唯一约束

-- ----------------------------------------------------------------------------------------------------------------------
-- (1)唯一约束作用在单列字段上面


-- ----------------------------------------------------
-- 删除原来的表
DROP TABLE IF EXISTS STUS;

-- 创建唯一约束的时候可以使用简化方式
CREATE TABLE STUS
(
    ID    INT(4),
    SNAME CHAR(4),
    AGE   INT(2),
    TEL   BIGINT(11) UNIQUE -- 使用简化方式,给TEL字段添加唯一约束
);

-- 插入测试数据
INSERT INTO STUS
VALUES (1, "张1", 21, 18974210001);
INSERT INTO STUS
VALUES (2, "张2", 22, 18974210002);
INSERT INTO STUS
VALUES (3, "张1", 21, 18974210003);
INSERT INTO STUS
VALUES (4, "张4", 24, 18974210003); -- 插入失败,TEL字段数据重复
INSERT INTO STUS
VALUES (5, "张5", 25, NULL);
INSERT INTO STUS
VALUES (6, "张6", 26, NULL);
-- NULL表示没有数据.NULL和NULL是不重复的!


-- ----------------------------------------------------
-- 创建唯一约束的时候也可以使用标准方式
USE
bjpowernode;

DROP TABLE IF EXISTS STUS;

-- 创建唯一约束的时候可以使用简化方式
CREATE TABLE STUS
(
    ID    INT(4),
    SNAME CHAR(4),
    AGE   INT(2),
    TEL   BIGINT(11),
    -- 创建约束的标准方式:约束的关键字 +约束名称 + 约束类型 + 作用字段
    CONSTRAINT TEL_UNI UNIQUE (TEL)
);

-- 插入测试数据
INSERT INTO STUS
VALUES (1, "张1", 21, 18974210001);
INSERT INTO STUS
VALUES (2, "张2", 22, 18974210002);
INSERT INTO STUS
VALUES (3, "张1", 21, 18974210003);
INSERT INTO STUS
VALUES (4, "张4", 24, 18974210003); -- 插入失败,TEL字段数据重复
INSERT INTO STUS
VALUES (5, "张5", 25, NULL);
INSERT INTO STUS
VALUES (6, "张6", 26, NULL);
-- NULL表示没有数据.NULL和NULL是不重复的!

-- 查询数据
SELECT *
FROM STUS;

-- 数据库约束和数据库表一样,都是一个独立的数据库对象,保存在系统数据库中
-- 数据库中的表保存在INFORMATION_SCHEMA数据库的TABLES表中
USE
INFORMATION_SCHEMA; -- 切换到INFORMATION_SCHEMA数据库

-- 查询bjpowernode数据库中的表
SELECT *
FROM TABLES
WHERE TABLE_SCHEMA = 'BJPOWERNODE';


-- 数据库中的约束保存在INFORMATION_SCHEMA数据库的 TABLE_CONSTRAINTS 表中
SELECT *
FROM TABLE_CONSTRAINTS
WHERE CONSTRAINT_SCHEMA = "bjpowernode";

-- ----------------------------------------------------------------------------------------------------------------------
-- (2)一个唯一约束可以同时作用在多列字段上面,只能使用标准方式创建约束
USE
BJPOWERNODE;

DROP TABLE IF EXISTS STUS;

-- 创建唯一约束的时候可以使用简化方式
CREATE TABLE STUS
(
    ID    INT(4),
    SNAME CHAR(4),
    AGE   INT(2),
    TEL   BIGINT(11),
    -- 创建约束的标准方式:约束的关键字 +约束名称 + 约束类型 + 作用字段
    -- 一个唯一约束同时作用在SNAME和TEL字段上面,保证SNAME+TEL数据的组合不能重复
    CONSTRAINT TEL_NAME_UNI UNIQUE (SNAME, TEL)
);

-- 插入测试数据
INSERT INTO STUS
VALUES (1, "张1", 21, 18974210001); -- 成功
INSERT INTO STUS
VALUES (2, "张1", 22, 18974210002); -- 成功
INSERT INTO STUS
VALUES (3, "张1", 21, 18974210003); -- 成功
INSERT INTO STUS
VALUES (4, "张1", 24, 18974210003); -- 插入失败,SNAME+TEL字段组合,数据重复
INSERT INTO STUS
VALUES (5, NULL, 24, 18974210003); -- 成功 ,唯一约束可以为空
INSERT INTO STUS
VALUES (6, NULL, 26, 18974210003);
-- 成功 ,唯一约束可以为空


-- 查询数据
SELECT *
FROM STUS;

-- (3)在一个表中可以同时有多个唯一约束,可以使用标准方式创建,有可以使用简化方式创建
-- 注意:使用简化方式创建约束的时候,数据库会自动为约束命名
-- 使用标准方式创建约束的时候,是我们自己给约束命名
DROP TABLE IF EXISTS STUS;

-- 创建唯一约束的时候可以使用简化方式
CREATE TABLE STUS
(
    ID    INT(4),
    SNAME CHAR(4) UNIQUE,
    AGE   INT(2),
    TEL   BIGINT(11) UNIQUE
);


CREATE TABLE STUS
(
    ID    INT(4),
    SNAME CHAR(4),
    AGE   INT(2),
    TEL   BIGINT(11),
    CONSTRAINT SNAME_UNI UNIQUE (SNAME),
    CONSTRAINT TEL_UNI UNIQUE (TEL)
);