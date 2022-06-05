#!/usr/local/bin/lua

print("\r\n====================-循环控制语句-====================")
print("循环控制语句用于控制程序的流程， 以实现程序的各种结构方式  Lua 支持以下循环控制语句：")

print("\r\n====================-break 语句	退出当前循环或语句，并开始脚本执行紧接着的语句-====================")
print("break 语句插入在循环体中，用于退出当前循环或语句，并开始脚本执行紧接着的语句")
print("如果你使用循环嵌套，break语句将停止最内层循环的执行，并开始执行的外层的循环语句")
--[ 定义变量 --]
a = 10
--[ while 循环 --]
while (a < 20)
do
    print("a 的值为:", a)
    a = a + 1
    if (a > 15)
    then
        --[ 使用 break 语句终止循环 --]
        break
    end
end
--a 的值为:    10
--a 的值为:    11
--a 的值为:    12
--a 的值为:    13
--a 的值为:    14
--a 的值为:    15


print("\r\n====================-while 循环 在条件为 true 时，让程序重复地执行某些语句。执行语句前会先检查条件是否为 true。-====================")

a = 10
while (a < 20)
do
    print("a 的值为:", a)
    a = a + 1
end
--a 的值为:    10
--a 的值为:    11
--a 的值为:    12
--a 的值为:    13
--a 的值为:    14
--a 的值为:    15
--a 的值为:    16
--a 的值为:    17
--a 的值为:    18
--a 的值为:    19

print("\r\n====================-for 循环 重复执行指定语句，重复次数可在 for 语句中控制。 -====================")
print("Lua 编程语言中 for语句有两大类 数值for循环 泛型for循环")
print("Lua 编程语言中数值 for 循环语法格式")
--for var=exp1,exp2,exp3 do
--    <执行体>
--end
--var 从 exp1 变化到 exp2，每次变化以 exp3 为步长递增 var，并执行一次 "执行体"。exp3 是可选的，如果不指定，默认为1
print("for的三个表达式在循环开始前一次性求值，以后不再进行求值。比如上面的f(x)只会在循环开始前执行一次，其结果用在后面的循环中。")
x = 5
function f(x)
    return x + 1
end
for i = 1, f(x) do
    print(i)
end
print("end")
for i = 5, 1, -1 do
    print(i)
end

print("泛型for循环")
print("泛型 for 循环通过一个迭代器函数来遍历所有值，类似 java 中的 foreach 语句")
print("i是数组索引值，v是对应索引的数组元素值。ipairs是Lua提供的一个迭代器函数，用来迭代数组")
--打印数组a的所有值
a = { "one", "two", "three" }
for i, v in ipairs(a) do
    print(i, v)
end

print("案例 循环数组 days")
days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }
for i, v in ipairs(days) do
    print(i + " " + v)
end

print("\r\n====================-repeat...until	重复执行循环，直到 指定的条件为真时为止-====================")
print("Lua 编程语言中 repeat...until 循环语句不同于 for 和 while循环，for 和 while 循环的条件语句在当前循环执行开始时判断，而 repeat...until 循环的条件语句在当前循环结束后判断")
--[ 变量定义 --]
a = 10
--[ 执行循环 --]
repeat
    print("a的值为:", a)
    a = a + 1
until (a > 15)
--a的值为:    10
--a的值为:    11
--a的值为:    12
--a的值为:    13
--a的值为:    14
--a的值为:    15
print("\r\n====================-循环嵌套	可以在循环内嵌套一个或多个循环语句（while do ... end;for ... do ... end;repeat ... until;）-====================")
j = 2
for i = 2, 10 do
    for j = 2, (i / j), 2 do
        if (not (i % j))
        then
            break
        end
        if (j > (i / j)) then
            print("i 的值为：", i)
        end
    end
end
--i 的值为：	8
--i 的值为：	9
--i 的值为：	10
print("\r\n====================-goto 语句	将程序的控制点转移到一个标签处-====================")
print("Lua 语言中的 goto 语句允许将控制流程无条件地转到被标记的语句处")
print("2022年6月5日 idea无法识别::::语法 原因未知")
--local a = 1
--::label::
--print("--- goto label ---")
--
--a = a+1
--if a < 3 then
--    goto label   -- a 小于 3 的时候跳转到标签 label
--end

-- --- goto label ---
print("从输出结果可以看出，多输出了一次 --- goto label ---")

print("以下实例演示了可以在 label 中设置多个语句：")
--i = 0
--:: s1 ::
--do
--    print(i)
--    i = i + 1
--end
--if i > 3 then
--    os.exit()   -- i 大于 3 时退出
--end
--goto s1
--0
--1
--2
--3
print(" goto，我们可以实现 continue 的功能")
--for i = 1, 3 do
--    if i <= 2 then
--        print(i, "yes continue")
--        goto continue
--    end
--    print(i, " no continue")
--    :: continue ::
--    print([[i'm end]])
--end
--1   yes continue
--i'm end
--2   yes continue
--i'm end
--3    no continue
--i'm end


print("\r\n====================-无限循环-====================")
--while (true)
--do
--    print("循环将永远执行下去")
--end