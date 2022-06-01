-- 3.AVG()函数,只能适用于数值型的数据,查询数值型数据的平均值

-- 查询所有员工的平均工资
SELECT AVG(月薪) AVG月薪1, SUM(月薪) / COUNT(*) AVG月薪2
FROM 员工表;

-- 查询所有员工的平均津贴
SELECT AVG(津贴) AVGCOMM1, SUM(津贴) / COUNT(*) AVGCOMM2, SUM(津贴) / COUNT(津贴) AVGCOMM3
FROM 员工表;