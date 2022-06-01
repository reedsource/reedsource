--在 SQL 中增加 HAVING 子句原因是，WHERE 关键字无法与聚合函数一起使用。
--HAVING 子句可以让我们筛选分组后的各组数据。

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
HAVING AVG月薪 = (
    SELECT MAX(DS.AVG月薪) 最高薪水
    FROM (SELECT AVG(月薪) AVG月薪 FROM 员工表 GROUP BY 部门编号) DS
)

-- | 部门编号 | AVG月薪 |
-- | :--- | :--- |
-- | 10 | 2270 |
