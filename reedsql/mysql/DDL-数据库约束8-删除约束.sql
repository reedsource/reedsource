-- 8.删除约束
-- 数据库约束和数据表都是独立的数据库对象,约束必须要作用在表上面
-- 我们删除表的时候,会连同约束一起删除;但是我们删除约束的时候,并不会影响表的结构和表中的数据

-- ----------------------------------------------------------------------------------------------------------------------
-- (1)删除主键约束
-- 在一个表中只能有唯一的一个主键约束,所以删除主键约束的时候不需要指明主键约束名称
CREATE TABLE 部门1
(
    部门编号 INT(4) PRIMARY KEY,
    部门名称 VARCHAR(10),
    部门位置 VARCHAR(20)
);

INSERT INTO 部门1
VALUES (10, "开发部", "北京");
INSERT INTO 部门1
VALUES (20, "销售部", "北京");
INSERT INTO 部门1
VALUES (30, "技术部", "北京");

-- 查询数据
SELECT *
FROM 部门1;

-- 由于主键约束的存在,下面的语句执行失败
INSERT INTO 部门1
VALUES (30, "生产部", "北京");

-- 删除主键约束
ALTER TABLE 部门1 DROP PRIMARY KEY;

-- 由于删除了主键约束,下面的语句可以执行成功
INSERT INTO 部门1
VALUES (30, "生产部", "北京");

-- ----------------------------------------------------------------------------------------------------------------------
-- (2).删除外键约束
-- 在一个表中可以有多个外键约束,所以删除外键约束的时候必须要指明外键约束的名称

CREATE TABLE 部门1
(
    部门编号 INT(4) PRIMARY KEY,
    部门名称 VARCHAR(10),
    部门位置 VARCHAR(20)
);


-- 在子表中创建外键约束的时候,必须要使用标准方式
CREATE TABLE 员工1
(
    员工编号   INT(4) PRIMARY KEY,
    员工姓名   VARCHAR(10),
    工作     VARCHAR(10),
    领导编号   INT(4),
    部门外键ID INT(4), -- 用来做外键的字段,名称可以和父表的主键字段名称不同

    -- 子表的外键约束字段要引用父表的主键字段或唯一键字段
    CONSTRAINT 员工_部门_FK FOREIGN KEY (部门外键ID) REFERENCES 部门1 (部门编号)
);

-- 先插入父表的数据
INSERT INTO 部门1
VALUES (10, "开发部", "北京");
INSERT INTO 部门1
VALUES (20, "销售部", "北京");
INSERT INTO 部门1
VALUES (30, "技术部", "北京");

-- 后插入子表的数据
INSERT INTO 员工1
VALUES (1000, "张0", "软件工程师", 7369, 10); -- 外键值可以重复
INSERT INTO 员工1
VALUES (1001, "张1", "软件工程师", 7369, 10);
INSERT INTO 员工1
VALUES (1002, "张2", "软件工程师", 7369, 10);
INSERT INTO 员工1
VALUES (2000, "王0", "销售员", 7369, 20);
INSERT INTO 员工1
VALUES (2001, "王1", "销售员", 7369, 20);
INSERT INTO 员工1
VALUES (2002, "王2", "销售员", 7369, 20);

-- 查询数据
SELECT *
FROM 员工1;
SELECT *
FROM 部门1;

-- 由于外键约束的存在,下面的语句执行时候
DELETE
FROM 部门1
WHERE 部门编号 = 10;

-- 删除外键约束
ALTER TABLE 员工1 DROP FOREIGN KEY 员工_部门_FK;

-- 由于我们删除了外键约束,下面的语句可以执行成
DELETE
FROM 部门1
WHERE 部门编号 = 10;

-- ----------------------------------------------------------------------------------------------------------------------
-- (3)删除唯一约束
-- 在一个表中可以有多个唯一约束,所以在删除唯一约束的时候必须要指明唯一约束的名称(也就是创建唯一约束的时候必须要指明唯一约束的名称)
CREATE TABLE 部门1
(
    部门编号 INT(4) PRIMARY KEY,
    部门名称 VARCHAR(10),
    部门位置 VARCHAR(20),
    CONSTRAINT 部门名称_UNI UNIQUE (部门名称)
);

INSERT INTO 部门1
VALUES (10, "开发部", "北京");
INSERT INTO 部门1
VALUES (20, "销售部", "北京");
INSERT INTO 部门1
VALUES (30, "技术部", "北京");

SELECT *
FROM 部门1;

-- 由于唯一约束的出在存在,下面的语句执行失败
INSERT INTO 部门1
VALUES (40, "技术部", "北京");

-- 删除唯一约束,使用DROP INDEX
ALTER TABLE 部门1 DROP INDEX 部门名称_UNI;

-- 由于删除了唯一约束,所以下面的语句执行成功
INSERT INTO 部门1
VALUES (40, "技术部", "北京");

-- ----------------------------------------------------------------------------------------------------------------------
-- (4)删除非空约束
-- 注意:删除非空约束的时候不能使用DROP方式,而是要使用MODIFY
CREATE TABLE 部门1
(
    部门编号 INT(4) PRIMARY KEY,
    部门名称 VARCHAR(10) NOT NULL,
    部门位置 VARCHAR(20)
);

INSERT INTO 部门1
VALUES (10, "开发部", "北京");
INSERT INTO 部门1
VALUES (20, "销售部", "北京");
INSERT INTO 部门1
VALUES (30, "技术部", "北京");

SELECT *
FROM 部门1;

-- 由于非空约束的存在,下面的语句执行失败
INSERT INTO 部门1
VALUES (40, NULL, "北京");

-- 删除非空约束,把部门名称从NOT NULL改为NULL,也就是删除了非空约束
ALTER TABLE 部门1 MODIFY 部门名称 VARCHAR (10) NULL;

-- 由于删除了非空约束,所以下面的语句可以执行成功
INSERT INTO 部门1
VALUES (40, NULL, "北京");