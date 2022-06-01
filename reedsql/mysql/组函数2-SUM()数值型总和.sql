-- 2.SUM()函数,只能适用于数值型的数据,查询数值型数据的总会

-- 查询员工表的工资总和(把14个工资累计在一起)
SELECT SUM(月薪) SUM月薪
FROM 员工表;

-- 查询员工的津贴总和(把4个不为NULL的COMM进行累计,因为组函数会自动的忽略空值)
SELECT SUM(津贴)
FROM 员工表;

-- 查询员工月收入的总和(月收入 = 月薪 + 津贴)
-- 下面的语句结果是错误的,把4个月薪+COMM不为NULL的数据进行累计的
SELECT SUM(月薪 + 津贴) SUM月薪
FROM 员工表;

-- 我们可以使用IFNULL对COMM进行处理
SELECT SUM(月薪 + IFNULL(津贴, 0)) SUM月薪
FROM 员工表;

-- 查询员工月收入的总和(月收入 = 月薪 + 津贴),也可以使用下面的方式
SELECT (SUM(月薪) + SUM(津贴)) SUM月薪
FROM 员工表;