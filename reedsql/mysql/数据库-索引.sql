-- 数据库索引的使用

-- 数据库索引类似于书中的目录,可以加快数据库的查询速度
-- 数据库索引是一个独立的的数据库对象,保存在系统数据库中

-- 数据库中检索数据的方式有两种
-- 1.全盘扫描,效率低
SELECT *
FROM 员工表
WHERE 员工姓名 = "王八";
-- 2.通过索引查询数据,效率高

-- 在数据库中创建索引有两种方式
-- 1.数据库自动的创建索引,数据库会自动的为主键字段创建索引
-- 2.手动的创建索引

-- 什么样的字段应该创建索引呢?
-- 如果一个字段经常出现在查询条件中(WHERE子句,HAVING子句),我们就应该创建索引
-- 例如:我们给员工表的员工姓名字段创建索引
CREATE
    INDEX 员工姓名_INDEX ON 员工表 (员工姓名);

-- 数据库索引是一个独立的的数据库对象,保存在系统数据库中,我们可以查询到我们刚刚创建的索引
SHOW
INDEX FROM 员工表;

-- 删除索引并不会影响表中的数据和表的结构
DROP
    INDEX 员工姓名_INDEX ON 员工表;