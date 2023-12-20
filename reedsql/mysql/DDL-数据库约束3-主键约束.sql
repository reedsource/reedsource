-- 4.主键约束.主键约束从功能上来说,相当于非空切唯一
-- 主键约束和手动的非空切唯一的区别:
-- (1)在一个表中只能有一个主键约束,但是在一个表中可以有多个非空切唯一约束
-- (2)数据库会自动为主键约束创建索引(索引的内容后面会讲到);但是数据库不会为手动的非空切唯一约束创建索引
-- 主键约束所在的字段称为主键字段,主键字段中的具体的值称为主键值;例如员工表中的员工编号字段是主键字段,0001是一个主键值
-- 由于主键约束是不能重复切不能为空的,所以可以通过主键值在表中查询到唯一的一条记录
-- 主键约束可以作用在单列字段上面,也可以同时作用在多列字段上面(称为联合主句),所以主键约束是一个表计约束

-- ----------------------------------------------------------------------------------------------------------------------
-- (1)主键约束作用在单列字段上面

-- -------------------------------------------------------------
-- 创建主键约束的时候使用简化方式
DROP TABLE IF EXISTS STUS;

CREATE TABLE STUS
(
    SID   INT(4) PRIMARY KEY,
    SNAME CHAR(4),
    AGE   INT(2),
    TEL   BIGINT(11)
);

INSERT INTO STUS
VALUES (1, "张1", 21, 18974210001); -- 成功
INSERT INTO STUS
VALUES (2, "张1", 22, 18974210002); -- 成功
INSERT INTO STUS
VALUES (3, "张1", 21, 18974210003); -- 成功
INSERT INTO STUS
VALUES (3, "张4", 21, 18974210003); -- 错误,主键重复
INSERT INTO STUS
VALUES (NULL, "张4", 21, 18974210003); -- 错误,主键不能为空

SELECT *
FROM STUS;

-- -------------------------------------------------------------
-- 创建主键约束的时候也可以使用标准方式

DROP TABLE IF EXISTS STUS;

CREATE TABLE STUS
(
    SID   INT(4),
    SNAME CHAR(4),
    AGE   INT(2),
    TEL   BIGINT(11),
    CONSTRAINT STUS_PK PRIMARY KEY (SID)
);

INSERT INTO STUS
VALUES (1, "张1", 21, 18974210001); -- 成功
INSERT INTO STUS
VALUES (2, "张1", 22, 18974210002); -- 成功
INSERT INTO STUS
VALUES (3, "张1", 21, 18974210003); -- 成功
INSERT INTO STUS
VALUES (3, "张4", 21, 18974210003); -- 错误,主键重复
INSERT INTO STUS
VALUES (NULL, "张4", 21, 18974210003); -- 错误,主键不能为空

SELECT *
FROM STUS;


-- ----------------------------------------------------------------------------------------------------------------------
-- (2)一个主键约束同时作用在多列字段上面,称为联合主键.必须要使用标准方式进行创建
DROP TABLE IF EXISTS STUS;

CREATE TABLE STUS
(
    SID   INT(4),
    SNAME CHAR(4),
    AGE   INT(2),
    TEL   BIGINT(11),
    CONSTRAINT STUS_PK PRIMARY KEY (SID, SNAME) -- 联合主键同时作用在SID和SNAME字段上面
);

INSERT INTO STUS
VALUES (1, "张1", 21, 18974210001); -- 成功
INSERT INTO STUS
VALUES (2, "张1", 22, 18974210002); -- 成功
INSERT INTO STUS
VALUES (3, "张1", 21, 18974210003); -- 成功
INSERT INTO STUS
VALUES (3, "张1", 21, 18974210003); -- 失败,SID + SNAME的组合数据重复
INSERT INTO STUS
VALUES (NULL, "张2", 21, 18974210003); -- 失败,主键不能重复
INSERT INTO STUS
VALUES (4, NULL, 21, 18974210003); -- 失败,主键不能重复

SELECT *
FROM STUS;

-- ----------------------------------------------------------------------------------------------------------------------
-- (3)主键是不能重复切不能为空的,通过主键值可以确定唯一的一条记录
-- 在一个表中,应该把上面样的字段当做主键呢?
-- 我们不能用和数据有关的业务数据来当做主键,因为业务数据是有可能变化的!
-- 而应该用和数据无关的非业务数据(例如一个流水号或定长的字符)来当做主键


-- ----------------------------------------------------------------------------------------------------------------------
-- (4)在MySql和SqlServer中,有一种特殊的整形数据,也就是自增字段.我们可以通过自增字段来维护主键值
DROP TABLE IF EXISTS STUS;

CREATE TABLE STUS
(
    SID   INT(4) AUTO_INCREMENT, -- 设置SID是自增字段,默认的从1开始,按1递增
    SNAME CHAR(4),
    AGE   INT(2),
    TEL   BIGINT(11),
    CONSTRAINT STUS_PK PRIMARY KEY (SID)
);


-- 由于主键字段采用了自增字段,也就是有数据库来维护主键值,我们插入数据的时候就不需要插入主键值了
INSERT INTO STUS (SNAME, AGE, TEL)
VALUES ("张1", 21, 1390000001);
INSERT INTO STUS (SNAME, AGE, TEL)
VALUES ("张2", 21, 1390000001);
INSERT INTO STUS (SNAME, AGE, TEL)
VALUES ("张3", 21, 1390000001);
INSERT INTO STUS (SNAME, AGE, TEL)
VALUES ("张4", 21, 1390000001);
INSERT INTO STUS (SNAME, AGE, TEL)
VALUES ("张5", 21, 1390000001);
INSERT INTO STUS (SNAME, AGE, TEL)
VALUES ("张6", 21, 1390000001);

-- 查询数据
SELECT *
FROM STUS;

-- 现在自增字段已经使用到了6.下一个值应该是7;我们手动的插入7
INSERT INTO STUS (SID, SNAME, AGE, TEL)
VALUES (7, "张7", 21, 1390000001);

-- 使用自增字段的时候,数据库会自动的判断已经存在的数据,7已经被占用了,则自增字段自动+1,也就是8
INSERT INTO STUS (SNAME, AGE, TEL)
VALUES ("张8", 21, 1390000001);

-- 删除数据之后,自增字段并不会回退
DELETE
FROM STUS
WHERE SID = 8;
INSERT INTO STUS (SNAME, AGE, TEL)
VALUES ("张9", 21, 1390000001);