-- 10.使用not作为查询条件

-- 查询出薪水不是1600和薪水不是3000的员工
-- 方式1:
SELECT *
FROM 员工表
WHERE 月薪 NOT IN (1600, 3000);

-- 方式2:
SELECT *
FROM 员工表
WHERE 月薪 <> 1600
  AND 月薪 <> 3000;

-- 方式3:
SELECT *
FROM 员工表
WHERE NOT (月薪 = 1600 OR 月薪 = 3000);

-- 查询津贴不是NULL的员工
SELECT *
FROM 员工表
WHERE 津贴 IS NOT NULL;