-- 4.MAX() 函数,适用于三种主要类型

-- 更新数据
UPDATE 员工表
SET 月薪 = 5000
WHERE 员工编号 = 7369;
SELECT *
FROM 员工表
ORDER BY 月薪 ASC;


-- 查询所有员工的最高工资
SELECT MAX(月薪) MAX月薪
FROM 员工表;

-- 日期型的数据也是有大小的.日期型数据的大小可以由其字面值来决定 .例如"1987"小于"2018", 也就是时间越早,日期数据越小
-- 查询最大的雇佣日期,也就是最晚的雇佣日期
SELECT MAX(受雇日期) "最晚的雇佣日期"
FROM 员工表;
SELECT *
FROM 员工表
ORDER BY 受雇日期 DESC;

-- 字符型的数据也是有大小写的
-- MySql中的字符编码和Java中的字符编码是相同的,都是采用Unicode编码,所以MySql中字符的大小和Java中字符的大小的规则是一样的
-- 都是按照字符的编码值来决定的. 例如:a的编码是97 ,b的编码是98
-- 查询员工中最大的名字
SELECT MAX(员工姓名) MAX员工姓名
FROM 员工表;


UPDATE 员工表
SET 月薪 = 800
WHERE 员工编号 = 7369;