-- 多表查询,也称为跨表查询,联合查询,也就是从多个表中查询相关的数据

-- 多表查询的分类
-- 1.按照语法的年代分
--   (1)SQL1992标准:FROM 后面是多个表
--   (2)SQL1999标准:FROM后面是一个表,通过JOIN方式连接其他的表

-- 2.按照连接的方式分
--   (1)内连接:可以查询满足一一对应关系的数据;例如:这个员工有所属的部门,这个部门有所属的员工,这样的数据满足一一对应关系,可以用
--      内连接查询出来
--   (2)外连接:可以查询不满足一一对应关系的数据;例如:有的员工没有部门,有的部门没有员工,这样的数据不满足一一对应的关系,使用内连接
--      是查询不到的,但是可以使用外连接进行查询


-- ======================================================================================================================
-- 内连接:可以查询满足一一对应关系的数据;例如:这个员工有所属的部门,这个部门有所属的员工,这样的数据满足一一对应关系,
-- 可以用内连接查询出来

-- 内连接的分类
-- 1.等值连接
-- 2.非等值连接
-- 3.自连接

-- ----------------------------------------------------------------------------------------------------------------------
-- 1.等值连接,建立在父子表关系之上用=来连接两个表

-- ---------------------------------------------------------
-- (1)SQL1992标准(FROM 后面跟多个表)

-- 查询员工信息及其部门信息
-- 当两个表中有重复的字段名称的时候,为了区分不同表中的字段,可以使用表名作为前缀
-- 在连接查询的时候,如果没有加入正确的连接条件,会产生两个表记录相乘的结果,这种现象称为笛卡尔乘积.这是错误的
SELECT 员工表.员工编号,
       员工表.员工姓名,
       员工表.工作,
       员工表.领导编号,
       员工表.受雇日期,
       员工表.月薪,
       员工表.部门编号,
       部门表.部门编号,
       部门表.部门名称,
       部门表.部门位置
FROM 员工表,
     部门表;

-- 如何避免迪卡尔乘积呢,需要加入正确的连接条件
SELECT 员工表.员工编号,
       员工表.员工姓名,
       员工表.工作,
       员工表.领导编号,
       员工表.受雇日期,
       员工表.月薪,
       员工表.部门编号,
       部门表.部门编号,
       部门表.部门名称,
       部门表.部门位置
FROM 员工表,
     部门表
WHERE 员工表.部门编号 = 部门表.部门编号;

-- 上面的语句中,员工表.部门编号 和部门.部门编号有等值的关系,所以只要差一个就可以了
SELECT 员工表.员工编号,
       员工表.员工姓名,
       员工表.工作,
       员工表.领导编号,
       员工表.受雇日期,
       员工表.月薪,
       部门表.部门编号,
       部门表.部门名称,
       部门表.部门位置
FROM 员工表,
     部门表
WHERE 员工表.部门编号 = 部门表.部门编号;

-- 使用表名作为前缀,如果表名很长,这样写忒麻烦了!我们可以给表起个简单的别名!
SELECT E.员工编号,
       E.员工姓名,
       E.工作,
       E.领导编号,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置
FROM 员工表 E,
     部门表 D
WHERE E.部门编号 = D.部门编号;


-- 在等值连接的基础上,我们可以使用AND,加入其它的查询条件
SELECT E.员工编号,
       E.员工姓名,
       E.工作,
       E.领导编号,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置
FROM 员工表 E,
     部门表 D
WHERE E.部门编号 = D.部门编号
  AND E.员工编号 = 7369;

-- ---------------------------------------------------------
-- (2)SQL1999标准,FROM后面是一个表,通过JOIN连接其它的表
SELECT E.员工编号,
       E.员工姓名,
       E.工作,
       E.领导编号,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置
FROM 员工表 E
         INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号);

-- 可以使用AND加入其它的查询条件
SELECT E.员工编号,
       E.员工姓名,
       E.工作,
       E.领导编号,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置
FROM 员工表 E
         INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号) AND E.员工编号 = 7369;

-- 也可以使用WHERE加入其它的查询条件,这是首先方式
SELECT E.员工编号,
       E.员工姓名,
       E.工作,
       E.领导编号,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置
FROM 员工表 E
         INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号)
WHERE E.员工编号 = 7369;

-- 使用INNER JOIN的时候,INNER可以忽略
SELECT E.员工编号,
       E.员工姓名,
       E.工作,
       E.领导编号,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置
FROM 员工表 E
         JOIN 部门表 D ON (E.部门编号 = D.部门编号)
WHERE E.员工编号 = 7369;


-- 查询部门编号是20的员工信息及其部门信息
-- SQL1992标准
SELECT *
FROM 员工表 E,
     部门表 D
WHERE E.部门编号 = D.部门编号
  AND D.部门编号 = 20;

-- SQL1999标准
SELECT E.员工编号, E.员工姓名, E.工作, E.月薪, D.部门编号, D.部门名称, D.部门位置
FROM 员工表 E
         JOIN 部门表 D ON (E.部门编号 = D.部门编号)
WHERE D.部门编号 = 20;


SELECT *
FROM 员工表;
SELECT *
FROM 部门表;

-- ----------------------------------------------------------------------------------------------------------------------
-- 2.内连接之非等值连接:两个表之间没有父子关系,用非等号来连接两个表

SELECT *
FROM 员工表;
SELECT *
FROM 工资级别表;

-- 查询员工信息及其工资级别信息
-- SQL1992标准
SELECT E.员工编号, E.员工姓名, E.月薪, S.等级, S.最低工资, S.最高工资
FROM 员工表 E,
     工资级别表 S
WHERE E.月薪 BETWEEN S.最低工资 AND S.最高工资
  AND E.员工编号 = 7369;


-- SQL1999标准
SELECT E.员工编号, E.员工姓名, E.月薪, S.等级, S.最低工资, S.最高工资
FROM 员工表 E
         INNER JOIN 工资级别表 S ON (E.月薪 BETWEEN S.最低工资 AND S.最高工资)
WHERE S.等级 = 2;

-- ----------------------------------------------------------------------------------------------------------------------
-- 3.内连接之自连接:可以通过别名将一个表虚拟成两个表(父子表),然后在这两个表上面做等值连接

SELECT *
FROM 员工表;

-- 查询员工信息及其经理信息

-- SQL1992标准
SELECT E.员工编号 "员工编号", E.员工姓名 "员工姓名", M.员工编号 "经理编号", M.员工姓名 "经理姓名"
FROM 员工表 E,
     员工表 M
WHERE E.领导编号 = M.员工编号
  AND E.员工编号 = 7369;

-- SQL1999标准
SELECT E.员工编号 "员工编号", E.员工姓名 "员工姓名", M.员工编号 "经理编号", M.员工姓名 "经理姓名"
FROM 员工表 E
         INNER JOIN 员工表 M ON (E.领导编号 = M.员工编号)
WHERE E.员工编号 = 7369;