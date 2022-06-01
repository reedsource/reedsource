-- 1.COUNT()函数,查询数据的总条数
-- COUNT()有两种用法
-- (1)COUNT(*) 查询数据总数
-- (2)COUNT(字段) 查询指定字段不为NULL的数据总数

-- ----------------------------------------------------------------------------------------------------------------------
-- (1)COUNT(*) 查询数据总数

-- 查询员工标的数据总数
SELECT *
FROM 员工表;

-- 员工总条数
SELECT COUNT(*)
FROM 员工表;

-- (2)COUNT(字段) 查询指定字段不为NULL的数据总数

-- 津贴不为null数据
SELECT *
FROM 员工表
WHERE 津贴 IS NOT NULL;

-- 津贴不为null总数
SELECT COUNT(津贴)
FROM 员工表;