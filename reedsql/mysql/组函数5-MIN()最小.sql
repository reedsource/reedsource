-- 5.MIN()函数,使用与三种主要类型,查询最小的数据

-- 查询最小的工资
SELECT MIN(月薪) MIN月薪
FROM 员工表;

-- 查询最小的名字
SELECT MIN(员工姓名) MIN员工姓名
FROM 员工表;

-- 查询最小的雇佣日期,也就是最早的雇佣日期
SELECT MIN(受雇日期) MIN受雇日期
FROM 员工表;