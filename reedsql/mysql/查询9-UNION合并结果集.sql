-- 使用UNION合并结果集(所谓的结果集就是查询结果),也就是把两个查询语句的查询结果合并成一个查询结果
-- 使用UNION合并结果集的时候,要求两个结果集的结构必须要一致(查询结果中字段的个数,字段的类型,字段的顺序必须要一致)

-- UNION有两种用方法
-- 1.UNION :会合并相同的数据;
-- 2.UNION ALL:不会合并相同的数据

-- 使用外连接的时候,有全外连接(FULL OUTER JOIN ON).但是MySql不支持全外连接.我们可以使用UNION实现全外连接的效果


-- ----------------------------------------------------------------------------------------------------------------------
-- 1.UNION :会合并相同的数据;
SELECT 员工编号,
       员工姓名,
       工作,
       领导编号,
       受雇日期,
       月薪,
       津贴,
       部门编号
FROM 员工表
WHERE 月薪 > 2000
UNION
SELECT 员工编号,
       员工姓名,
       工作,
       领导编号,
       受雇日期,
       月薪,
       津贴,
       部门编号
FROM 员工表
WHERE 月薪 > 3000;

-- ----------------------------------------------------------------------------------------------------------------------
-- 2.UNION ALL:不会合并相同的数据
SELECT 员工编号,
       员工姓名,
       工作,
       领导编号,
       受雇日期,
       月薪,
       津贴,
       部门编号
FROM 员工表
WHERE 月薪 > 2000
UNION ALL
SELECT 员工编号,
       员工姓名,
       工作,
       领导编号,
       受雇日期,
       月薪,
       津贴,
       部门编号
FROM 员工表
WHERE 月薪 > 3000;


-- ----------------------------------------------------------------------------------------------------------------------
-- 使用外连接的时候,有全外连接(FULL OUTER JOIN ON).但是MySql不支持全外连接.我们可以使用UNION实现全外连接的效果

-- 更新数据
UPDATE 员工表
SET 部门编号 = NULL
WHERE 员工编号 = 7369;

-- 使用UNION把左外连接和右外连接合并起来,实现全外连接的效果
SELECT E.员工编号, E.员工姓名, D.部门编号, D.部门名称
FROM 员工表 E
         LEFT OUTER JOIN 部门表 D ON (E.部门编号 = D.部门编号)
UNION
SELECT E.员工编号, E.员工姓名, D.部门编号, D.部门名称
FROM 员工表 E
         RIGHT OUTER JOIN 部门表 D ON (E.部门编号 = D.部门编号);

-- ----------------------------------------------------------------------------------------------------------------------
-- 查询包含 经理 和 销售经理 的员工信息

-- 方式1:
SELECT *
FROM 员工表
WHERE 工作 IN ("经理", "销售经理");

-- 方式2:
SELECT *
FROM 员工表
WHERE 工作 = "经理"
   OR 工作 = "销售经理";

-- 方式3:
SELECT *
FROM 员工表
WHERE 工作 = "经理"
UNION
SELECT *
FROM 员工表
WHERE 工作 = "销售经理";