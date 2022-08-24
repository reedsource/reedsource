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
::天翼网盘
del /f /s /q  C:\Users\reedbook\AppData\Roaming\eCloudUpgrade\*.*

::idea
del /f /s /q  C:\Users\reedbook\AppData\Local\JetBrains\IntelliJIdea2022.2\index

::edge
del /f /s /q  "C:\Program Files (x86)\Microsoft\EdgeUpdate\Download\*.*"

::思源笔记
del /f /s /q  C:\Users\reedbook\AppData\Local\siyuan-updater

@Echo on
pause
