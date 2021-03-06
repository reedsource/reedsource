-- 多行函数:每次将多条数据当做参数输入给函数,得到是多条记录对应的单行结果
-- 多行函数也称为组函数,也称为聚合函数

-- 使用多行函数的注意事项:
-- 1.多行函数会自动的忽略空值,不需要手动用WHERE排除空值
-- 2.多行函数不能出现在WHERE子句中

-- 组函数不能出现在WHERE子句中

-- 多个组函数可以在一起使用
SELECT MAX(月薪) MAX月薪, MIN(月薪) MIN月薪, AVG(月薪) AVG月薪, SUM(月薪) SUM月薪
FROM 员工表;

-- 查询工资比平均工资低的员工的信息
SELECT *
FROM 员工表
WHERE 月薪 < AVG(月薪);