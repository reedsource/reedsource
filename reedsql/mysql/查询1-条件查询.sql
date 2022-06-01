-- 条件查询语句,也就是在执行查询语句的时候加入适当的查询条件.这样只有满足查询条件的数据才会被显示
-- 条件查询语句,需要使用WHERE字句;(什么是子句呢?也就是可有可无的语句)
/*
条件查询语句的语法
SELECT 字段列表
FROM 表名
[WHERE 查询条件]

语句执行的顺序
1.SELECT FROM 查询出全部的数据
2.使用WHERE对查询结果进行过滤,只有满足条件的数据才会被显示

-- 数据库中的三种主要类型
-- 1.数值型,可以有小数,有可以没有小数
-- 2.字符型,可以表示一个或多个字段
-- 3.日期型

-- 在CMD客户端中,SQL语句的末尾必须要用分号结束;在窗口客户端中,SQL语句末尾可以有分号,也可以没有分号,为了良好的习惯,语句末尾用分号结束
-- SQL语句不区分大小写
*/

-- ======================================================================================================================
-- 1.使用=作为查询条件,适用于三种主要类型(数值型,日期型,字符型)
-- 对于字符型的数据和日期型的数据,必须要使用""或''

-- 查询员工姓名是"赵一"的员工信息
SELECT *
FROM 员工表
WHERE 员工姓名 = "赵一";

-- 查询工资是3000的员工的信息
SELECT *
FROM 员工表
WHERE 月薪 = 3000;

-- 查询雇佣日期(HIREDTAE)是"1987-04-19"的员工信息
SELECT *
FROM 员工表
WHERE 受雇日期 = "1987-04-19";

-- SQL语句是不区分大小写的.但是对于表中的字符型数据是区分大小写的
-- 例如:Oracle严格的区分字符型数据的大小写
-- MySql在Unix/Linux平台上面是严格区分字符型数据的大小写的,但是在window平台上面不区分字符型数据大小写
SELECT *
FROM 员工表
WHERE 工作 = "经理";

-- 安装在win上面的,有可能不区分大小写
SELECT *
FROM 员工表
WHERE 员工姓名 = "Dxx";

-- BINARY关键字 严格的区分字符型数据的大小写
SELECT *
FROM 员工表
WHERE BINARY 员工姓名 = "dxx";


-- ======================================================================================================================
-- 2.使用<> 或 != 作为查询条件,适用于三种主要类型

-- 查询工资不等于800的员工
SELECT *
FROM 员工表
WHERE 月薪 != 800;

SELECT *
FROM 员工表
WHERE 月薪 <> 800;

-- 查询名字不是"赵一"的员工信息
SELECT *
FROM 员工表
WHERE 员工姓名 <> "赵一";

SELECT *
FROM 员工表
WHERE 员工姓名 != "赵一";

-- 查询雇佣日期不是"1981-02-20"的员工信息
SELECT *
FROM 员工表
WHERE 受雇日期 != "1981-02-20";


IS NULL作为查询条件
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

-- ======================================================================================================================
-- 6.使用AND作为查询条件
-- 在执行条件查询语句的时候,可以使用多个查询条件.多个查询条件可以使用AND连接
-- AND要求多个查询条件必须要同时成立,相等于Java中的&&

-- 查询工资>1600 并且部门编号是20的员工
SELECT *
FROM 员工表
WHERE 月薪 > 1600
  AND 部门编号 = 20;

-- AND也可以使用 && 来替代
SELECT *
FROM 员工表
WHERE 月薪 > 1600 && 部门编号 = 20;

-- 刚才我们用的between and 也可以使用and来实现
-- 查询工资是1600~3000之间的员工
SELECT *
FROM 员工表
WHERE 月薪 BETWEEN 1600 AND 3000;

SELECT *
FROM 员工表
WHERE 月薪 >= 1600
  AND 月薪 <= 3000;


-- ======================================================================================================================
-- 7.使用OR作为查询条件
-- 在执行条件查询语句的时候,可以使用多个查询条件.多个查询条件可以使用OR连接
-- OR要求多个查询条件中只要满足一个条件即可.相当于Java中的||

-- 查询工资>=1600 或部门编号是20的员工
SELECT *
FROM 员工表
WHERE 月薪 >= 1600
   OR 部门编号 = 20;

-- OR也可以使用||来替代
SELECT *
FROM 员工表
WHERE 月薪 >= 1600 || 部门编号 = 20;

-- ======================================================================================================================
-- 8.AND和OR可以一起使用.注意首先要执行AND,后执行OR
-- AND和OR的优先级必须要记住!但是在搞不清楚的时候,可以使用()改变运算的顺序

-- 查询薪水大于1800，并且部门编号为20或30的员工
SELECT *
FROM 员工表
WHERE 月薪 > 1800 AND 部门编号 = 20
   OR 部门编号 = 30;

-- 仔细分析查询条件,薪水大于1800是一个条件,部门编号为20或30是另一个条件,两条条件要同时成立
-- 所以上面语句的查询结果是错误的,就是因为首先执行的是AND,后执行的是OR,相当于如下的语句
SELECT *
FROM 员工表
WHERE (月薪 > 1800 AND 部门编号 = 20)
   OR (部门编号 = 30);

-- 所以:查询薪水大于1800，并且部门编号为20或30的员工 的正确语句如下
SELECT *
FROM 员工表
WHERE (月薪 > 1800)
  AND (部门编号 = 20 OR 部门编号 = 30);