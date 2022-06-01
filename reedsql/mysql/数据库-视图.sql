-- 视图:视图实际上就是一个命名的查询语句,也就是把查询语句的查询结果做成一个数据库对象,保存在数据库中
-- 视图都是和一个有效的查询语句绑定在一起的

-- 回顾子查询:出现在FROM后面的子查询,用来该主查询提供查询数据的,这个子查询的结果被当做一个虚表,我们可以从这个虚表中查询数据
-- 这个出现在FROM后面的的子查询实际上就是视图,只不过这个视图并没有显示的声明,也就没有保存在数据库中,用完就没有了

CREATE VIEW V_员工_INFO
AS
SELECT E.员工编号,
       E.员工姓名,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置,
       S.等级,
       S.最低工资,
       S.最高工资
FROM 员工表 E
         INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号)
         INNER JOIN 工资级别表 S ON (E.月薪 BETWEEN S.最低工资 AND S.最高工资);

-- 视图可以被看做是一个表,我们可以查询视图的结构,从视图中查询数据,就像操作表一样
DESC 员工表;
DESC V_员工_INFO;


-- 视图实际上是一个虚拟的表,我们冲视图中查询数据的时候,首先要执行视图绑定的查询语句得到一个查询结果
-- 并且把查询结果当做一个临时表,然后从这个临时表中查询数据(实际上就是出现在FROM后面的子查询)
SELECT *
FROM 员工表;
SELECT *
FROM V_员工_INFO
WHERE 员工编号 = 7499;


-- 删除视图的语句和删除表的语句是一样的
DROP TABLE IF EXISTS STUS;
DROP TABLE IF EXISTS V_员工_INFO;

-- ======================================================================================================================
-- 再创建一个视图
CREATE VIEW V_员工
AS
SELECT *
FROM 员工表
WHERE 月薪 > 2000;

SELECT *
FROM V_员工;

-- 视图可以被看做是一个表,我们甚至可以向视图中插入数据;
-- 视图是一个虚表,是不能保存数据的,我们向视图中插入数据,数据实际上是被插入到数据库的源表中的
INSERT INTO V_员工
VALUES (8000, '张1', '软件工程师', 7902, '1983-2-26', 1800, 100, 30);

SELECT *
FROM 员工表;


-- ======================================================================================================================
-- 视图的作用:
-- 1.可以隐藏数据的来源;
-- 2.可以提供开发效率(但是并不能提供查询效率)

-- -----------------------------------------------------------------------
SELECT V.*
FROM (SELECT E.员工编号,
             E.员工姓名,
             E.月薪,
             D.部门编号,
             D.部门名称,
             D.部门位置,
             S.等级,
             S.最低工资,
             S.最高工资
      FROM 员工表 E
               INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号)
               INNER JOIN 工资级别表 S ON (E.月薪 BETWEEN S.最低工资 AND S.最高工资)) V
WHERE V.员工编号 = 7499;

-- -----------------------------------------------------------------------
CREATE VIEW V_员工_INFO
AS
SELECT E.员工编号,
       E.员工姓名,
       E.月薪,
       D.部门编号,
       D.部门名称,
       D.部门位置,
       S.等级,
       S.最低工资,
       S.最高工资
FROM 员工表 E
         INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号)
         INNER JOIN 工资级别表 S ON (E.月薪 BETWEEN S.最低工资 AND S.最高工资);

SELECT *
FROM V_员工_INFO
WHERE 员工编号 = 7499;

-- ======================================================================================================================
-- 注意:创建视图的查询语句中,不能有出现在FROM后面的子查询
CREATE VIEW V_员工1
AS
SELECT V.*
FROM (SELECT E.员工编号,
             E.员工姓名,
             E.月薪,
             D.部门编号,
             D.部门名称,
             D.部门位置,
             S.等级,
             S.最低工资,
             S.最高工资
      FROM 员工表 E
               INNER JOIN 部门表 D ON (E.部门编号 = D.部门编号)
               INNER JOIN 工资级别表 S ON (E.月薪 BETWEEN S.最低工资 AND S.最高工资)) V