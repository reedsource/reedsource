#!/usr/local/bin/lua
print("string.find 返回匹配串\"开始和结束的下标\"（如果不存在匹配串返回nil）")
s, e = string.find("www.reed.com", "reed")
print(s, e)