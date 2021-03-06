-- 对查询结果进行排序,需要使用ORDEE BY字句
/*
语法
SELECT 字段列表
FROM 表
[WHERE 查询条件]
[ORDER BY 排序字段]
*/
-- ======================================================================================================================
-- 1.使用单个字段对查询结果进行排序

-- 查询结果默认是没有顺序的
SELECT *
FROM 员工表
WHERE 月薪 > 1600;

-- 使用order by 对查询结果排序,默认的是升序排列的
SELECT *
FROM 员工表
WHERE 月薪 > 1600
ORDER BY 月薪;

-- 我们可以指定排序的顺序
SELECT *
FROM 员工表
WHERE 月薪 > 1600
ORDER BY 月薪 ASC; -- 指定按照月薪升序排列

SELECT *
FROM 员工表
WHERE 月薪 > 1600
ORDER BY 月薪 DESC;
-- 指定按照月薪降序排列

-- ======================================================================================================================
-- 2.可以使用多个字段进行排序,每个字段都可以指定不同的排序规则
-- 查询员工表的信息,按照部门编号和月薪排序
SELECT *
FROM 员工表
ORDER BY 部门编号 ASC, 月薪 DESC;


-- ======================================================================================================================
-- 3.使用字段的别名进行排序

-- 查询员工的年薪,并且按照年薪降序排列
SELECT 员工编号, 员工姓名, 工作, 月薪, 月薪 * 12 AS "年薪"
FROM 员工表
ORDER BY 年薪 DESC;

-- ======================================================================================================================
-- 4.按照字段查询查询结果中的位置来排序
-- 在下面的查询结果中,月薪字段的位置是6,排序的时候使用ORDER BY 6,也就是按照月薪进行排列
-- 但是这种方式尽量不要使用,因为表的结构会发生变化
SELECT *
FROM 员工表
ORDER BY 6;
