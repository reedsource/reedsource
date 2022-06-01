-- 数据库约束,注意,这样的语句也是属于DDL语句
-- 创建表的时候需要指明字段的信息,字段所需要的信息包括:字段名称,字段类型,字段长度,约束
-- 所谓的约束,也就是加在表中的(实际上是加在字段上面的)检测条件,只有符合条件的数据才能被操作(插入,更新,删除)
-- 数据库约束和数据库表一个,都是一个独立的数据库对象,都单独的保存在系统数据库中

-- 数据库约束的分类(从约束的范围上分):
-- 1.列级约束:如果一个约束只能作用在单独一列字段上面,这就是一个列级约束
-- 2.表级约束:如果一个约束可以同时作用在多列字段上面,这就是一个表级约束

-- 约束的具体分类
-- 1.非空约束(NOT NULL),是唯一的一个列级约束
-- 2.唯一约束(UNIQUE)
-- 3.主键约束(PRIMARY KEY)
-- 4.外键约束(FOREIGN KEY)

-- 在一个表中可以同时有多个非空约束,多个唯一约束,多个外键约束;但是只能有唯一的一个主键约束

-- 创建约束的时间
-- 1.可以在创建表的时候一起创建约束
-- 2.可以在创建好表之后,使用ALTER语句单独给表添加约束

-- ======================================================================================================================
-- 1.非空约束(NOT NULL),是唯一的一个列级约束,保证数据不能为NULL

-- 删除原来的表
DROP TABLE IF EXISTS STUS;

-- 创建表
CREATE TABLE STUS
(
    SID   INT(4),
    SNAME CHAR(4) NOT NULL, -- 给SNAME字段添加非空约束
    AGE   INT(2)
);


-- 插入测试数据
INSERT INTO STUS
VALUES (1, '张1', 21);
INSERT INTO STUS
VALUES (2, '张2', NULL);
INSERT INTO STUS
VALUES (3, 'NULL', 23); -- 注意:'NULL'是一个有效的字符型数据
INSERT INTO STUS
VALUES (4, NULL, NULL);
-- 插入失败,SNAME不能为空

-- 查询数据
SELECT *
FROM STUS;

-- 在一个表中可以同时有多个非空约束
DROP TABLE IF EXISTS STUS;

CREATE TABLE STUS
(
    SID   INT(4),
    SNAME CHAR(3) NOT NULL,
    AGE   INT(2),
    TEL   BIGINT(11) NOT NULL
);
