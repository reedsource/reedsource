-- 分组查询,多表查询,子查询综合应用

UPDATE 员工表
SET 部门编号 = 20
WHERE 员工编号 = 0001;

-- ======================================================================================================================
-- 按照部门编号分组,查询最高工资,并且显示最高工资的员工姓名
-- 思路:
-- (1)按照部门编号分组,查询最高工资
SELECT 部门编号, MAX(月薪) MAX月薪
FROM 员工表
GROUP BY 部门编号;

-- (2)把上面的查询结果当做一个临时表T,和员工表进行连接查询
SELECT T.部门编号, T.MAX月薪, E.员工姓名
FROM 员工表 E
         INNER JOIN (SELECT 部门编号, MAX(月薪) MAX月薪
                     FROM 员工表
                     GROUP BY 部门编号) T ON (E.部门编号 = T.部门编号 AND E.月薪 = T.MAX月薪)
ORDER BY T.部门编号;

-- 校验数据
SELECT *
FROM 员工表
ORDER BY 部门编号, 月薪 DESC;

-- ======================================================================================================================
-- 哪些人的薪水在部门平均薪水之上
-- 思路
-- (1)按照部门编号分组,查询平均工资
SELECT 部门编号, AVG(月薪) AVG月薪
FROM 员工表
GROUP BY 部门编号;

-- (2)把上面的查询结果单做一个临时表T,和员工表进行连接查询
SELECT E.员工编号, E.员工姓名, E.月薪, T.部门编号, T.AVG月薪
FROM 员工表 E
         INNER JOIN (SELECT 部门编号, AVG(月薪) AVG月薪
                     FROM 员工表
                     GROUP BY 部门编号) T ON (T.部门编号 = E.部门编号 AND E.月薪 > T.AVG月薪)
ORDER BY T.部门编号;

-- 查询所有员工的上级领导，要求显示员工名和对应的领导名，并且要求查询出那些没有领导的员工
SELECT E.员工编号 "员工编号", E.员工姓名 "员工姓名", M.员工编号 "经理编号", M.员工姓名 "经理姓名"
FROM 员工表 E
         LEFT OUTER JOIN 员工表 M ON (E.领导编号 = M.员工编号);


SELECT E.员工编号 "员工编号", E.员工姓名 "员工姓名", M.员工编号 "经理编号", M.员工姓名 "经理姓名"
FROM 员工表 M
         RIGHT OUTER JOIN 员工表 E
                          ON (E.领导编号 = M.员工编号);