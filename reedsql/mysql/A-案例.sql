##
1.取得每个部门最高薪水的人员名称
-- 第一步：求出每个部门的最高薪水
SELECT 部门编号, MAX(月薪) 最高薪水
FROM 员工表
GROUP BY 部门编号;
-- | 部门编号 | 最高薪水 |
-- | :--- | :--- |
-- | 10 | 5000 |
-- | 20 | 3000 |
-- | 30 | 2850 |
-- 将以上查询结果当成一张临时表t(部门编号 | 最高薪水)

-- 第二步：将t表与emp表进行表连接，表连接的条件：t.deptno = e.deptno 数据过滤条件：t.maxsal = e.月薪
SELECT E.员工姓名, E.月薪, E.部门编号
FROM 员工表 E
         JOIN (SELECT 部门编号, MAX(月薪) 最高月薪 FROM 员工表 GROUP BY 部门编号) ES
              ON E.部门编号 = ES.部门编号 AND E.月薪 = ES.最高月薪
ORDER BY E.部门编号;

-- 第三步:查询结果
-- | 员工姓名 | 月薪 | 部门编号 |
-- | :--- | :--- | :--- |
-- | 冯九 | 5000 | 10 |
-- | 王八 | 3000 | 20 |
-- | 蒋十三 | 3000 | 20 |
-- | 吴六 | 2850 | 30 |


##
2.哪些人的薪水在部门平均薪水之上
-- 第一步：按照部门编号分组,求出部门的平均薪水
SELECT 部门编号, AVG(月薪) 平均月薪
FROM 员工表
GROUP BY 部门编号;

-- 临时表t
-- | 部门编号 | 平均月薪 |
-- | :--- | :--- |
-- | 10 | 2270 |
-- | 20 | 2175 |
-- | 30 | 1566.666667 |

-- 第二步：将t表与员工表表进行表连接，条件：t.deptno = e.deptno 数据过滤条件：e.月薪 > t.avgsal
SELECT E.部门编号, E.员工姓名, E.月薪, AVG月薪
FROM 员工表 E
         JOIN (SELECT 部门编号, AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号) DS
              ON E.部门编号 = DS.部门编号 AND E.月薪 > DS.AVG月薪;

-- | 部门编号 | 员工姓名 | 月薪 | AVG月薪 |
-- | :--- | :--- | :--- | :--- |
-- | 30 | 钱二 | 1600 | 1566.666667 |
-- | 20 | 李四 | 2975 | 2175 |
-- | 30 | 吴六 | 2850 | 1566.666667 |
-- | 10 | 郑七 | 2450 | 2270 |
-- | 20 | 王八 | 3000 | 2175 |
-- | 10 | 冯九 | 5000 | 2270 |
-- | 20 | 蒋十三 | 3000 | 2175 |


##
3.取得部门中
（所有人的
）平均薪水等级
-- 第一步:按照部门编号分组,计算每个部门的平均工资
SELECT 部门编号, AVG(月薪) AVG月薪
FROM 员工表
GROUP BY 部门编号;
-- | 部门编号 | AVG月薪 |
-- | :--- | :--- |
-- | 10 | 2270 |
-- | 20 | 2175 |
-- | 30 | 1566.666667 |


-- 第二步:将以上查询结果当成临时表t 和薪水级别表进行连接
SELECT DS.部门编号, DS.AVG月薪, G.等级
FROM 工资级别表 G
         JOIN (SELECT 部门编号, AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号) DS
              ON DS.AVG月薪 BETWEEN G.最低工资 AND G.最高工资;
-- | 部门编号 | AVG月薪 | 等级 |
-- | :--- | :--- | :--- |
-- | 10 | 2270 | 4 |
-- | 20 | 2175 | 4 |
-- | 30 | 1566.666667 | 3 |


##
4.不准用组函数
（MAX
），取得最高薪水
（给出两种解决方案
）
-- 第一种：按照平均工资降序排列,取第一条数据
select 月薪
from 员工表
order by 月薪 desc limit 1;
-- | 月薪 |
-- | :--- |
-- | 5000 |

-- 第二种方式：
-- (1)把员工表当做a表 查询a表的所有的工资
SELECT 月薪
FROM 员工表;

-- a表
-- | 月薪 |
-- | :--- |
-- | 800 |
-- | 1600 |
-- | 1250 |
-- | 2975 |
-- | 1250 |
-- | 2850 |
-- | 2450 |
-- | 3000 |
-- | 5000 |
-- | 1500 |
-- | 1100 |
-- | 950 |
-- | 3000 |
-- | 1300 |
-- | 1300 |
-- | 1300 |


-- (2)把员工表当做b表 查询b表的所有的工资
-- b表

-- (3)连接a表和b表,a表中的所有工资 < b表中的工资,也就是小于表中的最高工资
SELECT DISTINCT E.月薪
FROM 员工表 E
         JOIN 员工表 M ON (E.月薪 < M.月薪);
-- | 月薪 |
-- | :--- |
-- | 800 |
-- | 1250 |
-- | 1500 |
-- | 1100 |
-- | 950 |
-- | 1300 |
-- | 1600 |
-- | 2850 |
-- | 2450 |
-- | 2975 |
-- | 3000 |


-- (4)从员工表中查询数据,条件是sal不在上面的结果之内
SELECT 月薪
FROM 员工表
WHERE 月薪 NOT IN (SELECT DISTINCT E.月薪
                 FROM 员工表 E
                          JOIN 员工表 M ON (E.月薪 < M.月薪));

-- | 月薪 |
-- | :--- |
-- | 5000 |


##
5.取得平均薪水最高的部门的部门编号及平均薪水
（至少两个方案
）
-- 第一种方案：
-- (1)按照部门编号分组,求出每个部门的平均薪水.按照平均工资降序排列,去第一条数据
SELECT 部门编号, AVG(月薪) AVG月薪
FROM 员工表
GROUP BY 部门编号
ORDER BY AVG月薪 DESC LIMIT 1;

-- | 部门编号 | AVG月薪 |
-- | :--- | :--- |
-- | 10 | 2270 |


-- 第二种方案：
-- (1)按照部门编号分组,查询每个部门的平均工资
SELECT 部门编号, AVG(月薪) AVG月薪
FROM 员工表
GROUP BY 部门编号;
-- | 部门编号 | AVG月薪 |
-- | :--- | :--- |
-- | 10 | 2270 |
-- | 20 | 2175 |
-- | 30 | 1566.666667 |

-- (2)将以上查询结果当成临时表t(部门编号,AVG月薪) ,从临时表中查询最大的最高薪水
-- 在不使用group by 并且select后面出现聚集函数的话，那么所有被select的都应该是聚集函数，否则就会报错

SELECT MAX(DS.AVG月薪) 最高薪水
FROM (SELECT AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号) DS;

-- (3) 用 临时表 t 再去匹配第二步得出的最大值，看哪个部门的平均薪资等于第二步的值
SELECT 部门编号, AVG(月薪) AVG月薪
FROM 员工表
GROUP BY 部门编号
HAVING AVG月薪 = (SELECT MAX(DS.AVG月薪) 最高薪水
                FROM (SELECT AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号) DS);

-- | 部门编号 | AVG月薪 |
-- | :--- | :--- |
-- | 10 | 2270 |


##
6.取得平均薪水最高的部门的部门名称
-- (1)按照部门编号分组,查询每个部门的平均工资,并且查询出平均工资最高的部门
SELECT 部门编号, AVG(月薪) AVG月薪
FROM 员工表
GROUP BY 部门编号
HAVING AVG月薪 = (SELECT MAX(DS.AVG月薪) 最高薪水
                FROM (SELECT AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号) DS);

-- | 部门编号 | AVG月薪 |
-- | :--- | :--- |
-- | 10 | 2270 |

-- (2)将上面的查询结果当做一个临时表t,部门表d连接,条件是t.deptno = d.deptno
SELECT D.部门编号, D.部门名称, DS1.平均最高薪水
FROM 部门表 D
         JOIN (SELECT 部门编号, AVG(月薪) AS '平均最高薪水'
               FROM 员工表
               GROUP BY 部门编号
               HAVING 平均最高薪水 = (SELECT MAX(DS.AVG月薪) 平均最高薪水
                                FROM (SELECT AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号) DS)) DS1
              ON D.部门编号 = DS1.部门编号;
-- | 部门编号 | 部门名称 | 平均最高薪水 |
-- | :--- | :--- | :--- |
-- | 10 | 会计 | 2270 |


##
7.求平均薪水的等级最低的部门的部门名称
-- (1)求每个部门的平均薪水,查询最低的平均工资及其部门编号
-- 倒序取第一个
SELECT 部门编号, AVG(月薪) AVG月薪
FROM 员工表
GROUP BY 部门编号
ORDER BY AVG月薪 LIMIT 1;
-- | 部门编号 | AVG月薪 |
-- | :--- | :--- |
-- | 30 | 1566.666667 |

-- (2)将上面的查询结果单做临时表t和 薪水级别表连接,查询薪水的级别
SELECT DS.部门编号, DS.AVG月薪, G.等级
FROM 工资级别表 G
         JOIN (SELECT 部门编号, AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号 ORDER BY AVG月薪 ASC LIMIT 1) DS
              ON DS.AVG月薪 BETWEEN G.最低工资 AND G.最高工资;
-- | 部门编号 | AVG月薪 | 等级 |
-- | :--- | :--- | :--- |
-- | 30 | 1566.666667 | 3 |

-- (3)将上面的查询结果当做临时表,和部门表连接
SELECT D.部门编号, D.部门名称, DSG.AVG月薪, DSG.等级
FROM 部门表 D
         JOIN (SELECT DS.部门编号, DS.AVG月薪, G.等级
               FROM 工资级别表 G
                        JOIN (SELECT 部门编号, AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号 ORDER BY AVG月薪 ASC LIMIT 1) DS
                             ON DS.AVG月薪 BETWEEN G.最低工资 AND G.最高工资) DSG
              ON DSG.部门编号 = D.部门编号;

-- | 部门编号 | 部门名称 | AVG月薪 | 等级 |
-- | :--- | :--- | :--- | :--- |
-- | 30 | 销售员 | 1566.666667 | 3 |


##
8.取得比普通员工
（员工代码没有在领导编号上出现的
）的最高薪水还要高的经理人姓名
-- (1)找出来经理人,也就是出现在领导编号中的数据
SELECT DISTINCT 领导编号
FROM 员工表
WHERE 领导编号 IS NOT NULL;
-- | 领导编号 |
-- | :--- |
-- | 7902 |
-- | 7698 |
-- | 7839 |
-- | 7566 |
-- | 7788 |
-- | 7782 |


-- (2)找出普通员工,也就是员工编号没有出现在上面的查询结果中
SELECT 员工编号
FROM 员工表
WHERE 员工编号 NOT IN (SELECT DISTINCT 领导编号 FROM 员工表 WHERE 领导编号 IS NOT NULL);
-- | 员工编号 |
-- | :--- |
-- | 7369 |
-- | 7499 |
-- | 7521 |
-- | 7654 |
-- | 7844 |
-- | 7876 |
-- | 7900 |
-- | 7934 |
-- | 7938 |
-- | 7940 |


-- (3) 求出普通员工的最高薪水
SELECT MAX(月薪) 最高月薪
FROM 员工表
WHERE 员工编号 NOT IN (SELECT DISTINCT 领导编号 FROM 员工表 WHERE 领导编号 IS NOT NULL);
-- | 最高月薪 |
-- | :--- |
-- | 1600 |


-- (4)查询出经理的薪水,并且经理的薪水大于上面的查询结果
SELECT 员工姓名, 月薪
FROM 员工表
WHERE 月薪 > (SELECT MAX(月薪) 最高月薪 FROM 员工表 WHERE 员工编号 NOT IN (SELECT DISTINCT 领导编号 FROM 员工表 WHERE 领导编号 IS NOT NULL))
UNION
SELECT 员工姓名, 月薪
FROM 员工表
WHERE 员工编号 IN (SELECT DISTINCT 领导编号 FROM 员工表 WHERE 领导编号 IS NOT NULL);
-- | 员工姓名 | 月薪 |
-- | :--- | :--- |
-- | 李四 | 2975 |
-- | 吴六 | 2850 |
-- | 郑七 | 2450 |
-- | 王八 | 3000 |
-- | 冯九 | 5000 |
-- | 蒋十三 | 3000 |


##
9.取得薪水最高的前五名员工
SELECT *
FROM 员工表
ORDER BY 月薪 DESC LIMIT 5;
-- | 员工编号 | 员工姓名 | 工作 | 领导编号 | 受雇日期 | 月薪 | 津贴 | 部门编号 |
-- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
-- | 7839 | 冯九 | 董事长 | NULL | 1981-11-17 | 5000 | NULL | 10 |
-- | 7902 | 蒋十三 | 程序分析员 | 7566 | 1981-12-03 | 3000 | NULL | 20 |
-- | 7788 | 王八 | 程序分析员 | 7566 | 1987-04-19 | 3000 | NULL | 20 |
-- | 7566 | 李四 | 经理 | 7839 | 1981-04-02 | 2975 | NULL | 20 |
-- | 7698 | 吴六 | 经理 | 7839 | 1981-05-01 | 2850 | NULL | 30 |


##
10.取得薪水最高的第六到第十名员工
SELECT *
FROM 员工表
ORDER BY 月薪 DESC LIMIT 5,5;
-- | 员工编号 | 员工姓名 | 工作 | 领导编号 | 受雇日期 | 月薪 | 津贴 | 部门编号 |
-- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
-- | 7782 | 郑七 | 经理 | 7839 | 1981-06-09 | 2450 | NULL | 10 |
-- | 7499 | 钱二 | 销售经理 | 7698 | 1981-02-20 | 1600 | 300 | 30 |
-- | 7844 | 陈十 | 销售经理 | 7698 | 1981-09-08 | 1500 | 0 | 30 |
-- | 7940 | dxx | 职员 | 7782 | 1982-01-23 | 1300 | NULL | 10 |
-- | 7938 | DXX | 职员 | 7782 | 1982-01-23 | 1300 | NULL | 10 |


##
11.取得最后入职的5名员工
SELECT *
FROM 员工表
ORDER BY 受雇日期 DESC LIMIT 5;
-- | 员工编号 | 员工姓名 | 工作 | 领导编号 | 受雇日期 | 月薪 | 津贴 | 部门编号 |
-- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
-- | 7876 | 褚十一 | 职员 | 7788 | 1987-05-23 | 1100 | NULL | 20 |
-- | 7788 | 王八 | 程序分析员 | 7566 | 1987-04-19 | 3000 | NULL | 20 |
-- | 7938 | DXX | 职员 | 7782 | 1982-01-23 | 1300 | NULL | 10 |
-- | 7940 | dxx | 职员 | 7782 | 1982-01-23 | 1300 | NULL | 10 |
-- | 7934 | 沈十四 | 职员 | 7782 | 1982-01-23 | 1300 | NULL | 10 |


##
12.取得每个薪水等级有多少员工
-- (1)查询所有员工的薪水级别
SELECT 等级
FROM 员工表 E
         JOIN 工资级别表 G ON E.月薪 BETWEEN G.最低工资 AND G.最高工资
ORDER BY G.等级;
-- | 等级 |
-- | :--- |
-- | 1 |
-- | 1 |
-- | 1 |
-- | 2 |
-- | 2 |
-- | 2 |
-- | 2 |
-- | 2 |
-- | 3 |
-- | 3 |
-- | 4 |
-- | 4 |
-- | 4 |
-- | 4 |
-- | 4 |
-- | 5 |


-- (2)把上面的查询结果当做一个临时表t,在临时表中按照工资等级分组,计算每组的数据总数
SELECT G.等级, COUNT(G.等级)
FROM (SELECT 等级
      FROM 员工表 E
               JOIN 工资级别表 G ON E.月薪 BETWEEN G.最低工资 AND G.最高工资
      ORDER BY G.等级) G
GROUP BY G.等级;
-- | 等级 | COUNT\(G.等级\) |
-- | :--- | :--- |
-- | 1 | 3 |
-- | 2 | 5 |
-- | 3 | 2 |
-- | 4 | 5 |
-- | 5 | 1 |


##
14.列出所有员工及领导的名字
SELECT E.员工姓名,
       M.员工姓名 员工领导名称
FROM 员工表 E
         JOIN 员工表 M ON E.领导编号 = M.员工编号;
-- | 员工姓名 | 员工领导名称 |
-- | :--- | :--- |
-- | 赵一 | 蒋十三 |
-- | 钱二 | 吴六 |
-- | 孙三 | 吴六 |
-- | 李四 | 冯九 |
-- | 周五 | 吴六 |
-- | 吴六 | 冯九 |
-- | 郑七 | 冯九 |
-- | 王八 | 李四 |
-- | 陈十 | 吴六 |
-- | 褚十一 | 王八 |
-- | 卫十二 | 吴六 |
-- | 蒋十三 | 李四 |
-- | 沈十四 | 郑七 |
-- | DXX | 郑七 |
-- | dxx | 郑七 |


##
15.列出受雇日期早于其直接上级的所有员工编号
、姓名
、部门名称
--  思路:首先对EMP表做自连接,查询员工及其经理信息,条件是员工的雇佣日期小于其经理的雇佣日期.
--  然后再连接部门表,取出部门名称信息
SELECT E.员工编号,
       E.员工姓名,
       D.部门名称
FROM 员工表 E
         JOIN 员工表 M ON E.领导编号 = M.员工编号 AND E.受雇日期 < M.受雇日期
         JOIN 部门表 D ON E.部门编号 = D.部门编号;
-- | 员工编号 | 员工姓名 | 部门名称 |
-- | :--- | :--- | :--- |
-- | 7369 | 赵一 | 研究 |
-- | 7499 | 钱二 | 销售员 |
-- | 7521 | 孙三 | 销售员 |
-- | 7566 | 李四 | 研究 |
-- | 7698 | 吴六 | 销售员 |
-- | 7782 | 郑七 | 会计 |


##
16.列出部门名称和这些部门的员工信息
，同时列出那些没有员工的部门
-- 使用右外连接
SELECT E.员工编号, E.员工姓名, D.部门编号, D.部门名称
FROM 员工表 E
         RIGHT JOIN 部门表 D
                    ON E.部门编号 = D.部门编号;

-- | 员工编号 | 员工姓名 | 部门编号 | 部门名称 |
-- | :--- | :--- | :--- | :--- |
-- | 7369 | 赵一 | 20 | 研究 |
-- | 7499 | 钱二 | 30 | 销售员 |
-- | 7521 | 孙三 | 30 | 销售员 |
-- | 7566 | 李四 | 20 | 研究 |
-- | 7654 | 周五 | 30 | 销售员 |
-- | 7698 | 吴六 | 30 | 销售员 |
-- | 7782 | 郑七 | 10 | 会计 |
-- | 7788 | 王八 | 20 | 研究 |
-- | 7839 | 冯九 | 10 | 会计 |
-- | 7844 | 陈十 | 30 | 销售员 |
-- | 7876 | 褚十一 | 20 | 研究 |
-- | 7900 | 卫十二 | 30 | 销售员 |
-- | 7902 | 蒋十三 | 20 | 研究 |
-- | 7934 | 沈十四 | 10 | 会计 |
-- | 7938 | DXX | 10 | 会计 |
-- | 7940 | dxx | 10 | 会计 |
-- | NULL | NULL | 40 | 运营 |


##
17.列出至少有5个员工的所有部门
-- 按照部门分组查询每组的员工人数,然后用having过滤数据
SELECT 部门编号, COUNT(员工姓名) 员工数量
FROM 员工表
GROUP BY 部门编号
HAVING COUNT(员工姓名) >= 5;
-- | 部门编号 | 员工数量 |
-- | :--- | :--- |
-- | 10 | 5 |
-- | 20 | 5 |
-- | 30 | 6 |

SELECT 部门编号, COUNT(员工姓名) 员工数量
FROM 员工表
GROUP BY 部门编号
HAVING 员工数量 >= 5;

-- | 部门编号 | 员工数量 |
-- | :--- | :--- |
-- | 10 | 5 |
-- | 20 | 5 |
-- | 30 | 6 |


##
18.列出薪水比
“钱二
”多的所有员工信息
-- (1)首先查询SMITH的工资
SELECT 月薪
FROM 员工表
WHERE 员工姓名 = '钱二';
-- | 月薪 |
-- | :--- |
-- | 1600 |

-- (2)用上面的查询结果当做条件
SELECT *
FROM 员工表
WHERE 月薪 > (SELECT 月薪 FROM 员工表 WHERE 员工姓名 = '钱二');
-- | 员工编号 | 员工姓名 | 工作 | 领导编号 | 受雇日期 | 月薪 | 津贴 | 部门编号 |
-- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
-- | 7566 | 李四 | 经理 | 7839 | 1981-04-02 | 2975 | NULL | 20 |
-- | 7698 | 吴六 | 经理 | 7839 | 1981-05-01 | 2850 | NULL | 30 |
-- | 7782 | 郑七 | 经理 | 7839 | 1981-06-09 | 2450 | NULL | 10 |
-- | 7788 | 王八 | 程序分析员 | 7566 | 1987-04-19 | 3000 | NULL | 20 |
-- | 7839 | 冯九 | 董事长 | NULL | 1981-11-17 | 5000 | NULL | 10 |
-- | 7902 | 蒋十三 | 程序分析员 | 7566 | 1981-12-03 | 3000 | NULL | 20 |


##
19.列出所有
“销售经理
”（办事员
）的姓名及其部门名称
，部门人数
-- (1)查询所有办事员的姓名及其部门名称
SELECT E.员工姓名,
       E.工作,
       D.部门编号,
       D.部门名称
FROM 员工表 E
         JOIN 部门表 D ON E.部门编号 = D.部门编号
WHERE E.工作 = '销售经理';
-- | 员工姓名 | 工作 | 部门编号 | 部门名称 |
-- | :--- | :--- | :--- | :--- |
-- | 钱二 | 销售经理 | 30 | 销售员 |
-- | 孙三 | 销售经理 | 30 | 销售员 |
-- | 周五 | 销售经理 | 30 | 销售员 |
-- | 陈十 | 销售经理 | 30 | 销售员 |

-- (2)求出部门的人数(按照部门编号分组,查询各部门的人数)  当成一个临时表t2
SELECT 部门编号, COUNT(员工姓名)
FROM 员工表
GROUP BY 部门编号;
-- | 部门编号 | COUNT\(员工姓名\) |
-- | :--- | :--- |
-- | 10 | 5 |
-- | 20 | 5 |
-- | 30 | 6 |

-- (3)把上面的查询结果当做两个临时表,连接这两个表,查询部门的员工人数
SELECT T1.员工姓名,
       T1.工作,
       T1.部门编号,
       T1.部门名称,
       T2.部门人数
FROM (SELECT E.员工姓名,
             E.工作,
             D.部门编号,
             D.部门名称
      FROM 员工表 E
               JOIN 部门表 D ON E.部门编号 = D.部门编号
      WHERE E.工作 = '销售经理') T1
         JOIN (SELECT 部门编号,
                      COUNT(员工姓名) 部门人数
               FROM 员工表
               GROUP BY 部门编号) T2 ON T1.部门编号 = T2.部门编号;

-- | 员工姓名 | 工作 | 部门编号 | 部门名称 | 部门人数 |
-- | :--- | :--- | :--- | :--- | :--- |
-- | 钱二 | 销售经理 | 30 | 销售员 | 6 |
-- | 孙三 | 销售经理 | 30 | 销售员 | 6 |
-- | 周五 | 销售经理 | 30 | 销售员 | 6 |
-- | 陈十 | 销售经理 | 30 | 销售员 | 6 |


##
20.列出最低薪水大于1500的各种工作,及从事此工作的全部雇员人数
-- (1)查询每个岗位的最低工资(按照岗位分组,计算每个岗位的最低工资)
SELECT 工作, MIN(月薪) AS 最低工资
FROM 员工表
GROUP BY 工作;
-- | 工作 | 最低工资 |
-- | :--- | :--- |
-- | 程序分析员 | 3000 |
-- | 经理 | 2450 |
-- | 职员 | 800 |
-- | 董事长 | 5000 |
-- | 销售经理 | 1250 |

##
(2) 用having过滤大于1500的数据
SELECT 工作, MIN(月薪) AS 最低工资, COUNT(工作)
FROM 员工表
GROUP BY 工作
HAVING 最低工资 > 1500;
-- | 工作 | 最低工资 | COUNT\(工作\) |
-- | :--- | :--- | :--- |
-- | 程序分析员 | 3000 | 2 |
-- | 经理 | 2450 | 3 |
-- | 董事长 | 5000 | 1 |


##
21.列出在部门
“销售部
”<销售部>工作的员工的姓名
，假定不知道销售部门的部门编号
-- (1)查询'销售部'的部门编号
SELECT 部门编号
FROM 部门表
WHERE 部门名称 = '销售部';
-- | 部门编号 |
-- | :--- |
-- | 30 |

##
(2)查询EMP表,条件是deptno = 上面的查询结果
SELECT 部门编号, 员工姓名
FROM 员工表
WHERE 部门编号 = (SELECT 部门编号 FROM 部门表 WHERE 部门名称 = '销售部');
-- | 部门编号 | 员工姓名 |
-- | :--- | :--- |
-- | 30 | 钱二 |
-- | 30 | 孙三 |
-- | 30 | 周五 |
-- | 30 | 吴六 |
-- | 30 | 陈十 |
-- | 30 | 卫十二 |


##
22.列出薪金高于公司平均薪金的所有员工
，所在部门
、上级领导
、雇员的工资等级
-- (1)求出公司的平均薪水
SELECT AVG(月薪) AS AVG月薪
FROM 员工表;
-- | AVG月薪 |
-- | :--- |
-- | 1976.5625 |

##
(2)连接部门表(经理表),员工表(职员表),薪水级别表,员工表
SELECT D.部门名称,
       E.员工姓名,
       S.等级,
       B.员工姓名 AS LEADERNAME
FROM 员工表 E
         JOIN 部门表 D ON E.部门编号 = D.部门编号
         LEFT JOIN 员工表 B ON E.领导编号 = B.员工编号
         JOIN 工资级别表 S ON E.月薪 BETWEEN S.最低工资 AND S.最高工资
WHERE E.月薪 > (SELECT AVG(月薪) AS AVG月薪 FROM 员工表);
-- | 部门名称 | 员工姓名 | 等级 | LEADERNAME |
-- | :--- | :--- | :--- | :--- |
-- | 研究 | 李四 | 4 | 冯九 |
-- | 销售部 | 吴六 | 4 | 冯九 |
-- | 会计 | 郑七 | 4 | 冯九 |
-- | 研究 | 王八 | 4 | 李四 |
-- | 会计 | 冯九 | 5 | NULL |
-- | 研究 | 蒋十三 | 4 | 李四 |


##
23.列出与
“王八
”从事相同工作的所有员工及部门名称
-- (1)先求出SCOTT的工作岗位
SELECT 工作
FROM 员工表
WHERE 员工姓名 = '王八';
-- | 工作 |
-- | :--- |
-- | 程序分析员 |

SELECT D.部门名称,
       E.员工姓名
FROM 员工表 E
         JOIN 部门表 D ON E.部门编号 = D.部门编号
WHERE E.工作 = (SELECT 工作
              FROM 员工表
              WHERE 员工姓名 = '王八');
-- | 部门名称 | 员工姓名 |
-- | :--- | :--- |
-- | 研究 | 王八 |
-- | 研究 | 蒋十三 |


##
24.列出薪金等于部门30中员工的薪金的   其它的员工的姓名和薪金
-- 第一步：先找出部门30的员工薪金
SELECT DISTINCT 月薪
FROM 员工表
WHERE 部门编号 = 30;
-- | 月薪 |
-- | :--- |
-- | 1600 |
-- | 1250 |
-- | 2850 |
-- | 1500 |
-- | 950 |

SELECT *
FROM 员工表
WHERE 月薪 IN (SELECT DISTINCT 月薪 FROM 员工表 WHERE 部门编号 = 30)
  AND 部门编号 <> 30;

-- | 员工编号 | 员工姓名 | 工作 | 领导编号 | 受雇日期 | 月薪 | 津贴 | 部门编号 |
-- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |


##
25.列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金
、部门名称
-- 第一步：求出部门30的最高薪水
SELECT MAX(月薪) AS 最高月薪
FROM 员工表
WHERE 部门编号 = 30;
-- | 最高月薪 |
-- | :--- |
-- | 2850 |

SELECT D.部门名称,
       E.员工姓名,
       E.月薪
FROM 员工表 E
         JOIN 部门表 D ON E.部门编号 = D.部门编号
WHERE E.月薪 > (SELECT MAX(月薪) AS 最高月薪
              FROM 员工表
              WHERE 部门编号 = 30);
-- | 部门名称 | 员工姓名 | 月薪 |
-- | :--- | :--- | :--- |
-- | 研究 | 李四 | 2975 |
-- | 研究 | 王八 | 3000 |
-- | 会计 | 冯九 | 5000 |
-- | 研究 | 蒋十三 | 3000 |


##
26.列出在每个部门工作的员工数量
、平均工资和平均受雇年份
-- to_days(日期类型) -> 天数
-- (1)平均受雇年份
select avg((to_days(now()) - to_days(受雇日期)) / 365) as 平均受雇年份
from 员工表;
-- | 平均受雇年份 |
-- | :--- |
-- | 38.71472603 |


SELECT E.部门编号,
       COUNT(E.员工姓名) AS 员工数量,
       AVG(E.月薪)     AS AVG月薪,
       AVG(
                   (
                       TO_DAYS(NOW()) - TO_DAYS(E.受雇日期)
                       ) / 365
           )         AS 平均受雇年份
FROM 员工表 E
GROUP BY E.部门编号;

-- | 部门编号 | 员工数量 | AVG月薪 | 平均受雇年份 |
-- | :--- | :--- | :--- | :--- |
-- | 10 | 5 | 2270 | 39.14522000 |
-- | 20 | 5 | 2175 | 37.28000000 |
-- | 30 | 6 | 1566.666667 | 39.55160000 |


##
27.列出所有员工的姓名
、部门名称和工资
SELECT D.部门名称,
       E.员工姓名,
       E.月薪
FROM 员工表 E
         RIGHT JOIN 部门表 D
                    ON E.部门编号 = D.部门编号;
-- | 部门名称 | 员工姓名 | 月薪 |
-- | :--- | :--- | :--- |
-- | 研究 | 赵一 | 800 |
-- | 销售部 | 钱二 | 1600 |
-- | 销售部 | 孙三 | 1250 |
-- | 研究 | 李四 | 2975 |
-- | 销售部 | 周五 | 1250 |
-- | 销售部 | 吴六 | 2850 |
-- | 会计 | 郑七 | 2450 |
-- | 研究 | 王八 | 3000 |
-- | 会计 | 冯九 | 5000 |
-- | 销售部 | 陈十 | 1500 |
-- | 研究 | 褚十一 | 1100 |
-- | 销售部 | 卫十二 | 950 |
-- | 研究 | 蒋十三 | 3000 |
-- | 会计 | 沈十四 | 1300 |
-- | 会计 | DXX | 1300 |
-- | 会计 | dxx | 1300 |
-- | 运营 | NULL | NULL |


##
28.列出所有部门的详细信息和人数 临时表t
SELECT E.部门编号,
       COUNT(E.员工姓名) AS 部门人数
FROM 员工表 E
GROUP BY E.部门编号;
-- | 部门编号 | 部门人数 |
-- | :--- | :--- |
-- | 10 | 5 |
-- | 20 | 5 |
-- | 30 | 6 |

SELECT D.部门编号,
       D.部门名称,
       D.部门位置,
       IFNULL(T.部门人数, 0) AS 部门人数
FROM (SELECT E.部门编号,
             COUNT(E.员工姓名) AS 部门人数
      FROM 员工表 E
      GROUP BY E.部门编号) T
         RIGHT JOIN 部门表 D
                    ON T.部门编号 = D.部门编号;
-- | 部门编号 | 部门名称 | 部门位置 | 部门人数 |
-- | :--- | :--- | :--- | :--- |
-- | 10 | 会计 | 纽约 | 5 |
-- | 20 | 研究 | 大连 | 5 |
-- | 30 | 销售部 | 上海 | 6 |
-- | 40 | 运营 | 武汉 | 0 |


##
29.列出各种工作的最低工资及从事此工作的雇员姓名
-- (1)查询最低工资的
SELECT E.工作,
       MIN(E.月薪) AS 最低工资
FROM 员工表 E
GROUP BY E.工作;
-- | 工作 | 最低工资 |
-- | :--- | :--- |
-- | 程序分析员 | 3000 |
-- | 经理 | 2450 |
-- | 职员 | 800 |
-- | 董事长 | 5000 |
-- | 销售经理 | 1250 |

-- 将以上查询结果当成临时表t与员工表 表进行表连接
select e.工作,
       e.员工姓名,
       e.月薪,
       t.最低工资
from (SELECT E.工作,
             MIN(E.月薪) AS 最低工资
      FROM 员工表 E
      GROUP BY E.工作) t
         join
     员工表 e
     on
         t.工作 = e.工作
where t.最低工资 = e.月薪;
-- | 工作 | 员工姓名 | 月薪 | 最低工资 |
-- | :--- | :--- | :--- | :--- |
-- | 职员 | 赵一 | 800 | 800 |
-- | 销售经理 | 孙三 | 1250 | 1250 |
-- | 销售经理 | 周五 | 1250 | 1250 |
-- | 经理 | 郑七 | 2450 | 2450 |
-- | 程序分析员 | 王八 | 3000 | 3000 |
-- | 董事长 | 冯九 | 5000 | 5000 |
-- | 程序分析员 | 蒋十三 | 3000 | 3000 |


##
30.列出各个部门经理的最低薪金
select e.部门编号,
       min(e.月薪) as 最低薪金
from 员工表 e
where e.工作 = '经理'
group by e.部门编号;
-- | 部门编号 | 最低薪金 |
-- | :--- | :--- |
-- | 10 | 2450 |
-- | 20 | 2975 |
-- | 30 | 2850 |

##
31.列出所有员工的年工资
，按年薪从低到高排序
select 员工姓名, (月薪 + ifnull(津贴, 0)) * 12 as 年薪
from 员工表
order by 年薪 asc;
-- | 员工姓名 | 年薪 |
-- | :--- | :--- |
-- | 赵一 | 9600 |
-- | 卫十二 | 11400 |
-- | 褚十一 | 13200 |
-- | 沈十四 | 15600 |
-- | DXX | 15600 |
-- | dxx | 15600 |
-- | 陈十 | 18000 |
-- | 孙三 | 21000 |
-- | 钱二 | 22800 |
-- | 郑七 | 29400 |
-- | 周五 | 31800 |
-- | 吴六 | 34200 |
-- | 李四 | 35700 |
-- | 王八 | 36000 |
-- | 蒋十三 | 36000 |
-- | 冯九 | 60000 |

##
32.求出员工领导的薪水超过2900的员工名称和领导名称
select e.员工姓名,
       b.员工姓名 as 领导,
       b.月薪
from 员工表 e
         join
     员工表 b
     on
         e.领导编号 = b.员工编号
where b.月薪 > 2900;
-- | 员工姓名 | 领导 | 月薪 |
-- | :--- | :--- | :--- |
-- | 赵一 | 蒋十三 | 3200 |
-- | 李四 | 冯九 | 5000 |
-- | 吴六 | 冯九 | 5000 |
-- | 郑七 | 冯九 | 5000 |
-- | 王八 | 李四 | 2975 |
-- | 褚十一 | 王八 | 3000 |
-- | 蒋十三 | 李四 | 2975 |

##
33.求部门名称中带
“售
”字符的部门员工的工资合计
、部门人数
-- (1) 临时表t
select d.部门名称,
       e.*
from 员工表 e
         right join
     部门表 d
     on
         e.部门编号 = d.部门编号
where d.部门名称 like '%售%';
-- | 部门名称 | 员工编号 | 员工姓名 | 工作 | 领导编号 | 受雇日期 | 月薪 | 津贴 | 部门编号 |
-- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
-- | 销售部 | 7499 | 钱二 | 销售经理 | 7698 | 1981-02-20 | 1600 | 300 | 30 |
-- | 销售部 | 7521 | 孙三 | 销售经理 | 7698 | 1981-02-22 | 1250 | 500 | 30 |
-- | 销售部 | 7654 | 周五 | 销售经理 | 7698 | 1981-09-28 | 1250 | 1400 | 30 |
-- | 销售部 | 7698 | 吴六 | 经理 | 7839 | 1981-05-01 | 2850 | NULL | 30 |
-- | 销售部 | 7844 | 陈十 | 销售经理 | 7698 | 1981-09-08 | 1500 | 0 | 30 |
-- | 销售部 | 7900 | 卫十二 | 职员 | 7698 | 1981-12-03 | 950 | NULL | 30 |

-- (2)
select t.部门名称,
       sum(t.月薪)     as 工资合计,
       count(t.员工姓名) as 部门人数
from (select d.部门名称,
             e.*
      from 员工表 e
               right join
           部门表 d
           on
               e.部门编号 = d.部门编号
      where d.部门名称 like '%售%') t
group by t.部门名称;
-- | 部门名称 | 工资合计 | 部门人数 |
-- | :--- | :--- | :--- |
-- | 销售部 | 9400 | 6 |

##
34.给任职日期超过30年的员工加薪10%
update 员工表
set 月薪 = 月薪 * 1.1
where (to_days(now()) - to_days(受雇日期)) / 365 > 30;



