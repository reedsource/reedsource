-- 查询表的结构
-- 月薪字段的类型是double(7,2) ,表示数据是数值型的,总长度是7位,小数点后占2位
DESC 员工表;


-- DDL语句,数据库定义语句.包括CREATE(创建表) ,DROP(删除表) ,ALTER(修改表)
-- 执行DDL语句的时候,数据库返回0;

-- ======================================================================================================================
-- 1.CREATE ,数据库建表语句
CREATE TABLE STU
(
    ID           INT(4), -- 整形数值,在MySql中是建议长度是4位,Oracle中数据的长度是4位
    NAME         CHAR(4),-- 定长字符串,4个字符的长度
    AGE          INT(2),
    SEX          CHAR(1),
    ADDR         VARCHAR(30),
    TOSCHOOLTIME DATE    -- 最后一个字段后面没有逗号,注意:日期类型的数据后面没有字段长度
);

-- 查询表结构
DESC STU;

-- 向表中插入测试数据
INSERT INTO STU
VALUES (1, '张1', 21, '男', '北京大兴', '2017-8-19');
-- 插入成功

-- age字段的类型是int(2),表示数据最长是两位,范围从1~99.在Oracle中是严格遵守规则的
-- 但是在MySql中,是建议长度是2位,插入105也是可以的!
INSERT INTO STU
VALUES (2, '张2', 105, '男', '北京大兴', '2017-8-19');
-- 插入成功

-- mysql中采用的是UTF-8编码,也就是Unicode编码,一个汉字也是一个字符,所以sex字段插入"男"是可以的
INSERT INTO STU
VALUES (3, '张3', 105, '男', '北京大兴', '2017-8-19');

-- 查询数据
SELECT *
FROM STU;


-- 删除表,删除表的时候回连同表中的数据一起删除掉
DROP TABLE STU;

-- 在没有指定表的情况下,如果执行删除表的语句,会报错.我们可以使用如下的语句避免错误
-- 注意:这个语句只能在MySql中执行,其他的数据库都不支持这种语法!
DROP TABLE IF EXISTS STU;
-- 如果STU表存在就删除表,如果不存在,也不会报错


-- ----------------------------------------------------------------------------------------------------------------------
-- 在创建表的时候,我们可以给字段提供缺省值
CREATE TABLE STUS
(
    SID   INT(4),
    SNAME CHAR(4),
    AGE   INT(2),
    SEX   CHAR(1) DEFAULT "男" -- 给sex字段提供缺省值"男"
);

-- 注意:在执行插入语句的时候,如果没有插入相应的值,没有缺省值的字段或被插入NULL,有缺省值的字段会被插入缺省值
INSERT INTO STUS (SID, SNAME)
VALUES (1, "张1");

-- 对于提供缺省值的字段,当然也可以插入其他的值
INSERT INTO STUS (SID, SNAME, SEX)
VALUES (2, "张2", '女');

-- 查询数据
SELECT *
FROM STUS;


-- ----------------------------------------------------------------------------------------------------------------------
-- 我们可以把建表语句和查询语句结合起来,也就是把查询语句中的字段当做新建的表的字段,并且把查询语句的结果插入到新表中
-- 任何有效的查询语句都可以和建表语句结合

DROP TABLE IF EXISTS 员工_INFO;

CREATE TABLE 员工_INFO
AS
SELECT E.员工编号,
       E.员工姓名,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置,
       S.等级,
       S.最低工资,
       S.最高工资
FROM 员工表 E
         INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号)
         INNER JOIN 工资级别表 S ON (E.月薪 BETWEEN S.最低工资 AND S.最高工资);

-- 查询数据
SELECT *
FROM 员工_INFO;


-- ----------------------------------------------------------------------------------------------------------------------
-- 如果查询语句中没有结果,则仅仅是把查询语句中的字段当做新建表的字段
CREATE TABLE 员工_BAK
AS
SELECT *
FROM 员工表
WHERE 月薪 > 10000;

-- 查询数据
SELECT *
FROM 员工_BAK;

-- 我们可以把一个插入语句和一个查询语句结合起来,也就是把查询语句的结果插入到前面的表中
-- 要求插入数据的表的结构必须要和查询结果的结构必须一致(字段的个数,字段的类型,字段的顺序必须要一致)
INSERT INTO 员工_BAK
SELECT *
FROM 员工表
WHERE 部门编号 = 20;


-- ALTER语句,可以在不影响表中数据的情况下,修改表的结构
-- 对表结构的修改主要是对字段的修改,包括添加字段(ADD),删除字段(DROP),修改字段(MODIFY)

-- 删除原来的表
DROP TABLE IF EXISTS STUS;

-- 创建表
CREATE TABLE STUS
(
    SID   INT(4),
    SNAME CHAR(4),
    AGE   INT(3),
    SEX   CHAR(1)
);


-- 插入数据
INSERT INTO STUS
VALUES (1, '张1', 21, '男');
INSERT INTO STUS
VALUES (2, '张2', 22, '男');
INSERT INTO STUS
VALUES (3, '张3', 23, '男');
INSERT INTO STUS
VALUES (4, '张4', 24, '男');
INSERT INTO STUS
VALUES (5, '张5', 25, '男');
INSERT INTO STUS
VALUES (6, '张6', 26, '男');
INSERT INTO STUS
VALUES (7, '张7', 27, '男');
INSERT INTO STUS
VALUES (8, '张8', 28, '男');

-- 查询数据
SELECT *
FROM STUS;

-- 使用ALTER语句,给表中添加TEL字段.新添加的字段会被添加到表字段的最后面
ALTER TABLE STUS
    ADD TEL BIGINT(11);

-- 修改表中的字段
-- 当该字段没有数据的情况下,字段的类型和字段的长度都是可以修改的
ALTER TABLE STUS
    MODIFY TEL VARCHAR (11);
ALTER TABLE STUS
    MODIFY TEL VARCHAR (20);

-- 当字段中有数据的时候,增大字段的长度总是可以的,但是减少字段的长度,要根据已经存在的数据来决定
UPDATE STUS
SET SNAME = '张三四五'
WHERE SID = 3;
ALTER TABLE STUS
    MODIFY SNAME CHAR (30);


-- 但是减少字段的长度,要根据已经存在的数据来决定
-- 例如:SNAME字段的最大数据是'张三四五',长度是4个字符,所以把字段的长度减少为4是可以的.但是减少为3个字符会报错!
ALTER TABLE STUS
    MODIFY SNAME CHAR (3);

-- 查询表的结构
DESC STUS;


-- 当该字段有数据的时候,修改字段的类型要看存在的数据是否可以自动转换为新的类型
-- 例如:SID字段中存在的数据是数值型的数据1,2,3,4,5, 数值型的数据可以自动的转换为字符型,所以可以把SID从数值型改为字符型
ALTER TABLE STUS
    MODIFY SID CHAR (10);

-- 现在SID字段的类型是字符型,保存的数据是"1","2","3","4",这样的数据不能自动转化为日期类型,所以无法把sid改为日期类型
ALTER TABLE STUS
    MODIFY SID DATE;

-- 删除表中的字段
ALTER TABLE STUS
DROP
TEL;