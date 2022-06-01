-- 2.UPPER() ,把字符型数据转换为大写的
-- 在数据库严格区分大小写的情况下,查询姓名是'dxx'的员工(也就是让数据库忽略大小写)
SELECT *
FROM 员工表
WHERE BINARY 员工姓名 = UPPER("dxx"); -- 先把小写的smith转化为大写的SMITH,然后再去和员工姓名字段进行比较