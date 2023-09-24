@echo off

::删除文件案例
::del /f /s /q D:\cache\1.txt
::del /f /s /q D:\cache\reedtools\*.*

::删除文件夹案例
::rd /s /q D:\cache\path

::系统更新回档文件夹
del /f /s /q  C:\Windows\servicing\LCU\*.*
::下载系统补丁之后的缓存文件夹
del /f /s /q  C:\Windows\SoftwareDistribution\Download\*.*
::系统更新历史进程记录
del /f /s /q  C:\Windows\Logs\CBS\*.*
del /f /s /q  C:\Users\Public\Documents\*.*
::天翼网盘日志
del /f /s /q  C:\Users\reeds\AppData\Roaming\eCloudUpgrade\*.*
del /f /s /q  C:\Users\reeds\AppData\Roaming\ecloud\logs\*.*
del /f /s /q  C:\Users\reeds\AppData\Roaming\ecloud\Dump\*.*

::edge  中间有空格,所以需要用""包围
del /f /s /q  "C:\Program Files (x86)\Microsoft\EdgeUpdate\Download\*.*"

::思源笔记
del /f /s /q  C:\Users\reeds\AppData\Local\siyuan-updater

::JetBrains Toolbox缓存 20230924已经去除
::del /f /s /q  C:\Users\reeds\AppData\Local\JetBrains\Toolbox\download\*.*

@Echo on
pause
