-- DELETE :删除表中的数据
-- 在删除数据的时候可以加入条件,这样只会删除满足条件的数据;如果没有加入条件,则是删除所有的数据
DELETE
FROM 员工表
WHERE 员工编号 = 9008;

DELETE
FROM 员工表
WHERE 员工编号 > 9000;

DELETE
FROM 员工表;

-- 查询数据
SELECT *
FROM 员工表;