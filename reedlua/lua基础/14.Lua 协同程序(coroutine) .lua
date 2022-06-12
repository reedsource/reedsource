#!/usr/local/bin/lua
--什么是协同(coroutine)？
--Lua 协同程序(coroutine)与线程比较类似：拥有独立的堆栈，独立的局部变量，独立的指令指针，同时又与其它协同程序共享全局变量和其它大部分东西。
--协同是非常强大的功能，但是用起来也很复杂。
--线程和协同程序区别
--线程与协同程序的主要区别在于，一个具有多个线程的程序可以同时运行几个线程，而协同程序却需要彼此协作的运行。
--在任一指定时刻只有一个协同程序在运行，并且这个正在运行的协同程序只有在明确的被要求挂起的时候才会被挂起。
--协同程序有点类似同步的多线程，在等待同一个线程锁的几个线程有点类似协同。

--coroutine.create()	创建 coroutine，返回 coroutine， 参数是一个函数，当和 resume 配合使用的时候就唤醒函数调用
--coroutine.resume()	重启 coroutine，和 create 配合使用
--coroutine.yield() 	挂起 coroutine，将 coroutine 设置为挂起状态，这个和 resume 配合使用能有很多有用的效果
--coroutine.status()	查看 coroutine 的状态
--注：coroutine 的状态有三种：dead，suspended，running，具体什么时候有这样的状态请参考下面的程序
--coroutine.wrap（）	    创建 coroutine，返回一个函数，一旦你调用这个函数，就进入 coroutine，和 create 功能重复
--coroutine.running()	返回正在跑的 coroutine，一个 coroutine 就是一个线程，当使用running的时候，就是返回一个 corouting 的线程号

--以下实例演示了以上各个方法的用法
co = coroutine.create(
        function(i)
            print(i);
        end
)

coroutine.resume(co, 1)   -- 1
print(coroutine.status(co))  -- dead

print("----------")

co = coroutine.wrap(
        function(i)
            print(i);
        end
)

co(1)

print("----------")

co2 = coroutine.create(
        function()
            for i = 1, 10 do
                print(i)
                if i == 3 then
                    print(coroutine.status(co2))  --running
                    print(coroutine.running()) --thread:XXXXXX
                end
                coroutine.yield()
            end
        end
)

coroutine.resume(co2) --1
coroutine.resume(co2) --2
coroutine.resume(co2) --3

print(coroutine.status(co2))   -- suspended
print(coroutine.running())

print("----------")

--1
--dead
------------
--1
------------
--1
--2
--3
--running
--thread: 0x7fb801c05868    false
--suspended
--thread: 0x7fb801c04c88    true