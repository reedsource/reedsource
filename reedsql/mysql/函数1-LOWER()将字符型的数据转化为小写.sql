-- 1.LOWER():将字符型的数据转化为小写的
-- 用法:LOWER(字段或 字符型数据)

-- MySql在Window上面默认的是不区分字符型数据的大小写的,我们可以使用BINARY让MySql在window平台上也严格的区分大小写
-- 在数据库严格区分大小写的情况下,查询姓名是'dxx'的员工(也就是让数据库忽略大小写)
SELECT *
FROM 员工表
WHERE BINARY LOWER(员工姓名) = "dxx"; -- 先把员工姓名字段的结果转换为小写的,再去和'dxx'进行比较