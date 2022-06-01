#!/usr/local/bin/lua
print(type("Hello world"))      --> string
print(type(10.4*3))             --> number
print(type(print))              --> function
print(type(type))               --> function
print(type(true))               --> boolean
print(type(nil))                --> nil
print(type(type(X)))            --> string

print("================================nil========================================")
tab = { k1 = "a", k2 = "b", "c" }
for k, v in pairs(tab)
do
    print(k .. " - " .. v)
end

print("------------")

tab.k1 = nil
for k, v in pairs(tab) do
    print(k .. " - " .. v)
end

print("================================boolean========================================")
print(type(true))
print(type(false))
print(type(nil))

if false or nil then
    print("至少有一个是 true")
else
    print("false 和 nil 都为 false!")
end
if 3 then
    print("数字 0 是 true")
else
    print("数字 0 为 false")
end

print("================================number========================================")
print(type(2))
print(type(2.2))
print(type(0.2))
print(type(2e+1))
print(type(0.2e-1))
print(type(7.8263692594256e-06))

print("================================string========================================")
html = [[
<html>
<head></head>
<body>
    <a href="http://ireed.top/">菜鸟教程</a>
</body>
</html>
]]
print(html)



print("================================table========================================")
a = {}
a["key"] = "value"
key = 10
a[key] = 22
a[key] = a[key] + 11
for k, v in pairs(a) do
    print(k .. " : " .. v)
end



-- table_test2.lua 脚本文件
local tbl = {"apple", "pear", "orange", "grape"}
for key, val in pairs(tbl) do
    print("Key", key)
end





a3 = {}
for i = 1, 10 do
    a3[i] = i
end
a3["key"] = "val"
print(a3["key"])
print(a3["none"])



print("================================function========================================")
-- function_test.lua 脚本文件

function factorial1(n)
    if n == 0 then
        return 1
    else
        return n * factorial1(n - 1)
    end
end
print(factorial1(5))
factorial2 = factorial1
print(factorial2(5))




-- function_test2.lua 脚本文件
function testFun(tab,fun)
        for k ,v in pairs(tab) do
                print(fun(k,v));
        end
end


tab={key1="val1",key2="val2"};
testFun(tab,
function(key,val)--匿名函数
        return key.."="..val;
end
);



































