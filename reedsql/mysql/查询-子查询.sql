-- 子查询:用来该主查询提供查询条件或查询数据,而首先执行的一个查询,首先执行子查询,然后主查询使用子查询的查询结果
-- 为了语法的清晰,把子查询放到一个()里面

-- 子查询的分类
-- 1.出现在WHERE中的子查询,用来给主查询提供查询条件的
-- 2.出现在FROM中的子查询,用来给主查询提供查询数据的! 也就是把子查询新的查询结果虚拟成一个临时表,然后从这个临时表中查询数据
-- 3.出现在查询列表中的子查询.这种情况了解即可

-- 出现在WHERE中的子查询,是用来给主查询提供查询数据的!根据子查询返回的结果,有如下的分类
-- 1.子查询返回单行单列,使用单行比较操作符(<,<=,>,>=,!=)
-- 2.子查询返回多行单列,需要使用多行比较操作符(IN ,ALL,ANY)


-- ======================================================================================================================
-- 1.出现在WHERE中的子查询,用来给主查询提供查询条件的

-- 查询工资比平均工资低的员工的信息
-- (1)查询员工的平均工资
SELECT AVG(月薪) AVG月薪
FROM 员工表;

-- (2)把上面的查询结果定做条件
SELECT *
FROM 员工表
WHERE 月薪 < 2073;

-- (3)把上面的两个查询语句合并成一个查询语句
SELECT *
FROM 员工表
WHERE 月薪 < (SELECT AVG(月薪) AVG月薪 FROM 员工表);

-- ======================================================================================================================
-- 2.出现在FROM中的子查询,用来给主查询提供查询数据的! 也就是把子查询新的查询结果虚拟成一个临时表,然后从这个临时表中查询数据
SELECT V.*
FROM (SELECT E.员工编号,
             E.员工姓名,
             E.月薪,
             D.部门编号,
             D.部门名称,
             S.等级,
             S.最低工资,
             S.最高工资
      FROM 员工表 E
               INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号)
               INNER JOIN 工资级别表 S ON (E.月薪 BETWEEN S.最低工资 AND S.最高工资)) V
WHERE V.员工编号 = 3001;


-- ======================================================================================================================
-- 3.出现在查询列表中的子查询.功能类似于外连接的效果,这种情况了解即可.
SELECT E.员工编号, E.员工姓名, E.月薪, E.部门编号, (SELECT D.部门名称 FROM 部门表 D WHERE E.部门编号 = D.部门编号)
FROM 员工表 E;

-- ======================================================================================================================
-- 出现在WHERE中的子查询,是用来给主查询提供查询数据的!根据子查询返回的结果,有如下的分类

-- =============================================================================
-- 1.子查询返回单行单列,使用单行比较操作符(<,<=,>,>=,!=)
SELECT *
FROM 员工表
WHERE 月薪 < (SELECT AVG(月薪) AVG月薪 FROM 员工表);


-- =============================================================================
-- 2.子查询返回多行单列,需要使用多行比较操作符(IN ,ALL,ANY);

-- 下面语句的子查询中返回多行单列,是不能使用单行比较操作符的!,而是需要使用多行比较操作符
SELECT *
FROM 员工表
WHERE 月薪 < (SELECT 月薪 FROM 员工表 WHERE 部门编号 = 20);

-- -----------------------------------------------------------------------------
-- (1)使用IN操作符,也就是包含子查询的结果

-- 查询非20部门中具有和20部门相同职位的员工信息
SELECT *
FROM 员工表
WHERE 工作 IN (SELECT DISTINCT 工作 FROM 员工表 WHERE 部门编号 = 20)
  AND 部门编号 != 20;


-- -----------------------------------------------------------------------------
-- (2)使用ALL操作符

-- (i) > ALL:大于子查询的最大值
SELECT *
FROM 员工表
WHERE 月薪 > ALL (SELECT 月薪 FROM 员工表 WHERE 部门编号 = 20);

-- (ii) < ALL:小于子查询的最小值
SELECT *
FROM 员工表
WHERE 月薪 < ALL (SELECT 月薪 FROM 员工表 WHERE 部门编号 = 20);
-- -----------------------------------------------------------------------------
-- (3)使用ANY操作符

-- (i) > ANY:大于子查询的最小值
SELECT *
FROM 员工表
WHERE 月薪 > ANY (SELECT 月薪 FROM 员工表 WHERE 部门编号 = 20);

-- (ii) < ANY:小于子查询的最大值
SELECT *
FROM 员工表
WHERE 月薪 < ANY (SELECT 月薪 FROM 员工表 WHERE 部门编号 = 20);
