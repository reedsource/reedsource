#!/usr/local/bin/lua
-- test_module.lua 文件
-- module 模块为上文提到到 module.lua

require("analysis.module")

print(module.constant)

module.func3()

local m = require("analysis.module")

print(m.constant)

m.func3()


