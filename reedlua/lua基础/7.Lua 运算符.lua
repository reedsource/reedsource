#!/usr/local/bin/lua

print("\r\n====================-算术运算符-====================")
a = 25
b = 10
print("a的值", a, " b的值", b)
print("Line a + b 的值为 ", a + b)
print("Line a - b 的值为 ", a - b)
print("Line a * b 的值为 ", a * b)
print("Line a / b 除法运算包含小数 的值为 ", a / b)
print("Line a // b 整除(lua5.3添加) 的值为 ", a // b)
print("Line a % b 取余 的值为 ", a % b)
print("Line a ^ b 乘幂 的值为 ", a ^ 2)
print("Line a - b 负号 的值为 ", -a)

print("\r\n====================-关系运算符-====================")
a = 21
b = 10

if (a == b)
then
    print("Line 1 - a 等于 b")
else
    print("Line 1 - a 不等于 b")
end

if (a ~= b)
then
    print("Line 2 - a 不等于 b")
else
    print("Line 2 - a 等于 b")
end

if (a < b)
then
    print("Line 3 - a 小于 b")
else
    print("Line 3 - a 大于等于 b")
end

if (a > b)
then
    print("Line 4 - a 大于 b")
else
    print("Line 5 - a 小于等于 b")
end

-- 修改 a 和 b 的值
a = 5
b = 20
if (a <= b)
then
    print("Line 5 - a 小于等于  b")
end

if (b >= a)
then
    print("Line 6 - b 大于等于 a")
end

print("\r\n====================-逻辑运算符-====================")
a = true
b = true

if (a and b)
then
    print("a and b - 条件为 true")
end

if (a or b)
then
    print("a or b - 条件为 true")
end

print("---------分割线---------")

-- 修改 a 和 b 的值
a = false
b = true

if (a and b)
then
    print("a and b - 条件为 true")
else
    print("a and b - 条件为 false")
end

if (not (a and b))
then
    print("not( a and b) - 条件为 true")
else
    print("not( a and b) - 条件为 false")
end

print("\r\n====================-其他运算符-====================")
print("..	连接两个字符串")
print("#	一元运算符，返回字符串或表的长度")

a = "Hello "
b = "World"
print("连接字符串 a 和 b ", a .. b)
print("b 字符串长度 ", #b)
print("字符串 Test 长度 ", #"Test")
print("菜鸟教程网址长度 ", #"ireed.top")
--连接字符串 a 和 b     Hello World
--b 字符串长度     5
--字符串 Test 长度     4
--菜鸟教程网址长度     14


print("\r\n====================-运算符优先级-====================")
--^
--not    - (unary)
--*      /       %
--+      -
--..
--<      >      <=     >=     ~=     ==
--and
--or
a = 20
b = 10
c = 15
d = 5

e = (a + b) * c / d;-- ( 30 * 15 ) / 5
print("(a + b) * c / d 运算值为  :", e)

e = ((a + b) * c) / d; -- (30 * 15 ) / 5
print("((a + b) * c) / d 运算值为 :", e)

e = (a + b) * (c / d);-- (30) * (15/5)
print("(a + b) * (c / d) 运算值为 :", e)

e = a + (b * c) / d;  -- 20 + (150/5)
print("a + (b * c) / d 运算值为   :", e)

--(a + b) * c / d 运算值为  :    90.0
--((a + b) * c) / d 运算值为 :    90.0
--(a + b) * (c / d) 运算值为 :    90.0
--a + (b * c) / d 运算值为   :    50.0




