#!/usr/local/bin/lua

print("Lua 变量有三种类型：全局变量、局部变量、表中的域。")
print("Lua 中的变量全是全局变量，哪怕是语句块或是函数里，除非用 local 显式声明为局部变量。")
print("局部变量的作用域为从声明位置开始到所在语句块结束。")
print("变量的默认值均为 nil。")

print("====================-全局变量、局部变量-====================")
a = 5               -- 全局变量
local b = 5         -- 局部变量

function joke()
    c = 5           -- 全局变量
    local d = 6     -- 局部变量
    print(d)
end

joke()

print(c, d)          --> 5 nil

do
    local a = 6     -- 局部变量
    b = 6           -- 对局部变量重新赋值
    print(a, b);     --> 6 6
end

print(a, b)      --> 5 6

print("====================-赋值语句-====================")
print("赋值是改变一个变量的值和改变表域的最基本的方法")
print("Lua 可以对多个变量同时赋值，变量列表和值列表的各个元素用逗号分开，赋值语句右边的值会依次赋给左边的变量")
x = 3
a, b = 10, 2 * x
a = 10
b = 2 * x

print("遇到赋值语句Lua会先计算右边所有的值然后再执行赋值操作，所以我们可以这样进行交换变量的值")
a, b = b, a
print(a)
print(b)
print("当变量个数和值的个数不一致时，Lua会一直以变量个数为基础采取以下策略")
print("a. 变量个数 > 值的个数             按变量个数补足nil")
print("b. 变量个数 < 值的个数             多余的值会被忽略")
a, b, c = 0, 1
print(a, b, c)             --> 0   1   nil

a, b = a + 1, b + 1, b + 2     -- value of b+2 is ignored
print(a, b)               --> 1   2

print("错误案例")
a, b, c = 0
print(a, b, c)             --> 0   nil   nil
print("正确案例")
a, b, c = 0, 0, 0
print(a, b, c)

print("多值赋值经常用来交换变量，或将函数调用返回给变量")
function f()
    return 1, 2
end
a, b = f()
print("f()返回两个值，第一个赋给a，第二个赋给b。 ")
print(a)
print(b)
print("应该尽可能的使用局部变量，有两个好处")
print("1. 避免命名冲突。")
print("2. 访问局部变量的速度比全局变量更快。")

print("====================-索引-====================")
print("对 table 的索引使用方括号 []。Lua 也提供了 . 操作")
t = { 4, 5, 6, 7, 8 }
i = 3
print(t[i])
print("当索引为字符串类型时的一种简化写法")
print(t.i)
print("采用索引访问本质上是一个类似这样的函数调用 gettable_event(t, i)")
function gettable_event(t, m)
    for i = 1, m do
        if i == m then
            return t[i]
        end
    end
    return nil
end

print(gettable_event(t, 3))