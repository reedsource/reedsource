-- 使用IS NULL作为查询条件
-- NULL是数据中一种特殊的数据,表示没有数据.三种主要类型都支持NULL.
-- 对于NULL的判断,不能使用=NULL,而是要使用 IS NULL

-- 查询津贴是null的员工
SELECT *
FROM 员工表
WHERE 津贴 = NULL;

SELECT *
FROM 员工表
WHERE 津贴 IS NULL;

-- 查询津贴不为NULL的员工
SELECT *
FROM 员工表
WHERE 津贴 IS NOT NULL;