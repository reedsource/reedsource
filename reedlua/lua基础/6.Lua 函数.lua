#!/usr/local/bin/lua

print("lua格式定义如下")
print("函数范围 默认为全局函数 可以为空 如需要局部函数 添加local")
print("function 固定")
print("函数名称")
print("函数参数 多个参数逗号分割 可以为空")
print("函数体")
print("return 可以返回多个值 以逗号分割")
print("end")

print("\r\n====================-函数返回两个值的最大值-====================")
function max(num1, num2)
    if (num1 > num2) then
        result = num1;
    else
        result = num2;
    end
    return result;
end
-- 调用函数
print("两值比较最大值为 ", max(10, 4))
print("两值比较最大值为 ", max(5, 6))

print("\r\n====================-Lua 中我们可以将函数作为参数传递给函数-====================")

myPrint = function(param)
    print("这是打印函数 -   ##", param, "##")
end

function add(num1, num2, functionPrint)
    result = num1 + num2
    -- 调用传递的函数参数
    functionPrint(result)
end

myPrint(10)
-- myPrint 函数作为参数传递
add(2, 5, myPrint)

print("\r\n====================-多返回值-====================")
print("Lua函数可以返回多个结果值，比如string.find，其返回匹配串\"开始和结束的下标\"（如果不存在匹配串返回nil）")
function maximum (a)
    local mi = 1             -- 最大值索引
    local m = a[mi]          -- 最大值
    for i, val in ipairs(a) do
        if val > m then
            mi = i
            m = val
        end
    end
    return m, mi
end

print(maximum({ 8, 10, 23, 12, 5 }))

print("\r\n====================-可变参数-====================")
print("Lua 函数可以接受可变数目的参数，和 C 语言类似，在函数参数列表中使用三点 ... 表示函数有可变的参数")
print("计算几个数的平均值函数")
function average(...)
    result = 0
    local arg = { ... }    --> arg 为一个表，局部变量
    for i, v in ipairs(arg) do
        result = result + v
    end
    print("总共传入 " .. #arg .. " 个数")
    return result / #arg
end

print("平均值为", average(10, 5, 3, 4, 5, 6))

print("\r\n====================-select(\"#\",...) 来获取可变参数的数量-====================")
function average(...)
    result = 0
    local arg = { ... }
    for i, v in ipairs(arg) do
        result = result + v
    end
    print("总共传入 " .. select("#", ...) .. " 个数")
    return result / select("#", ...)
end

print("平均值为", average(10, 5, 3, 4, 5, 6))

print("\r\n====================-固定参数+变长参数-====================")
print("有时候我们可能需要几个固定参数加上可变参数，固定参数必须放在变长参数之前")
function fWrite(fmt, ...)
    ---> 固定的参数fmt
    return io.write(string.format(fmt, ...))
end

fWrite("reed\n")
--->fmt = "reed", 没有变长参数。
fWrite("%d%d\n", 1, 2)   --->fmt = "%d%d", 变长参数为 1 和 2


print("\r\n====================-select函数属性-====================")
print("select('#', …) 返回可变参数的长度")
print("select(n, …) 用于返回从起点 n 开始到结束位置的所有参数列表")
print("调用 select 时，必须传入一个固定实参 selector(选择开关) 和一系列变长参数。")
print("如果 selector 为数字 n，那么 select 返回参数列表中从索引 n 开始到结束位置的所有参数列表，")
print("否则只能为字符串 #，这样 select 返回变长参数的总数")

function f(...)
    a = select(3, ...)  -->从第三个位置开始，变量 a 对应右边变量列表的第一个参数
    print(a)
    print(select(3, ...)) -->打印所有列表参数
end

f(0, 1, 2, 3, 4, 5)
--2
--2       3       4       5

do
    function foo(...)
        for i = 1, select('#', ...) do
            -->获取参数总数
            local arg = select(i, ...); -->读取参数
            print("arg", arg);
        end
    end

    foo(1, 2, 3, 4);
end






