-- 7.级联更新 ON UPDATE CASCADE,也就是在更新父表数据的时候,会一起更新子表的相关数据
-- 在外键约束下,我们不能直接更新父表的主键字段
-- 我们可以在外键中设置级联更新,这样的话,我们可以直接更新父表数据(更新父表的主键字段),会连同子表的数据一起更新
-- 注意:级联更新必须要建立在外键约束之上
-- 注意:级联删除和级联更新一定要慎用,通常只会在系统测试的时候才会使用!
-- 系统正式运行之后,通常不会使用级联删除/级联更新.我们就是利用外键约束来保证数据的安全

CREATE TABLE 部门1
(
    部门编号 INT(4) PRIMARY KEY,
    部门名称 VARCHAR(10),
    部门位置 VARCHAR(20)
);


CREATE TABLE 员工1
(
    员工编号   INT(4) PRIMARY KEY,
    员工姓名   VARCHAR(10),
    工作     VARCHAR(10),
    领导编号   INT(4),
    部门外键ID INT(4),
    -- 注意:级联删除必须要建立在外键约束之上,也就是先建立外键约束,然后才能指定级联更新
    CONSTRAINT 员工_部门_FK FOREIGN KEY (部门外键ID) REFERENCES 部门1 (部门编号) ON UPDATE CASCADE
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
VALUES (1000, "张0", "软件工程师", 0001, 10); -- 外键值可以重复
INSERT INTO 员工1
VALUES (1001, "张1", "软件工程师", 0001, 10);
INSERT INTO 员工1
VALUES (1002, "张2", "软件工程师", 0001, 10);
INSERT INTO 员工1
VALUES (2000, "王0", "销售员", 0001, 20);
INSERT INTO 员工1
VALUES (2001, "王1", "销售员", 0001, 20);
INSERT INTO 员工1
VALUES (2002, "王2", "销售员", 0001, 20);

-- 查询数据
SELECT *
FROM 员工1;
SELECT *
FROM 部门1;

-- 由于我们指定了级联更新,我们可以直接更新父表的主键值,会两条子表相关的数据一起更新
UPDATE 部门1
SET 部门编号 = 100,
    部门名称= "研发部"
WHERE 部门编号 = 10;