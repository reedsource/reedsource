-- between and(在特定的范围之内)作为查询条件
-- between and对于字符型的数据和日期型的数据,是包括两个边界值的
-- between and对于字符型的数据比较特殊,包含第一个边界值,但是不包含第二个边界值

-- 查询工资在1600~3000之间的员工信息
SELECT *
FROM 员工表
WHERE 月薪 BETWEEN 1600 AND 3000;

-- 查询雇佣日期在"1981-02-20" ~ "1981-06-09"之间的员工信息
SELECT *
FROM 员工表
WHERE 受雇日期 BETWEEN "1981-02-20" AND "1981-06-09";

-- 查询名字在"A"~"F"之间的员工信息
SELECT *
FROM 员工表
WHERE 员工姓名 BETWEEN "A" AND "F";

-- 上面的查询语句中,包含第一个边界值,但是不包含第二个边界值.所以要查询"A"~"F"的员工,应该用如下的语句
-- 查询边界
SELECT *
FROM 员工表
WHERE 员工姓名 BETWEEN "A" AND "G";
