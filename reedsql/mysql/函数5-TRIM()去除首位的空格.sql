-- 5.TRIM(),去除字符型数据首位的空格,但是不会去除中间的空格
-- 用法: TRIM(字段或字符型数据)

-- 更新数据
UPDATE 员工表
SET 员工姓名 ="  赵 二   "
WHERE 员工编号 = 0001;

select *
from 员工表
where 员工编号 = 0001;

-- 恢复数据
UPDATE 员工表
SET 员工姓名 ="赵一"
WHERE 员工编号 = 0001;

-- 函数是可以嵌套的! LENGTH(TRIM(员工姓名)) ,首先执行里面的函数,把里面函数的结果当做参数输入给外面的函数
SELECT 员工编号, 员工姓名 "原来的名字", LENGTH(员工姓名) "原来名字的长度", TRIM(员工姓名) "去除首尾空格之后的名字", LENGTH(TRIM(员工姓名)) "去除首尾空格之后的名字的长度"
FROM 员工表
WHERE 员工编号 = 0001;