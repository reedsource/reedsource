#!/usr/local/bin/lua

-- 2022年6月8日 封存 需要外部环境LuaSQL支持 LuaSQL当前不支持lua5.4
luasql = require "luasql.mysql"

--创建环境对象
env = luasql.mysql()

--连接数据库
conn = env:connect("p1", "app", "sql123", "192.168.1.200", 3306)

--设置数据库的编码格式
conn:execute "SET NAMES UTF8"

--执行数据库操作
cur = conn:execute("select * from anchor_types")

row = cur:fetch({}, "a")

--文件对象的创建
file = io.open("role.txt", "w+");

while row do
    var = string.format("%d %s\n", row.id, row.name)
    print(var)
    file:write(var)
    row = cur:fetch(row, "a")
end

file:close()  --关闭文件对象
conn:close()  --关闭数据库连接
env:close()   --关闭数据库环境