@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
cd /d c:
echo 清理C盘中...请耐心等候
del /f /s /q %systemdrive%\*.log
del /f /s /q %windir%\*.bak
del /f /s /q %systemdrive%\*.tmp
del /f /s /q %systemdrive%\*._mp
del /f /s /q %systemdrive%\*.gid
del /f /s /q %systemdrive%\*.chk
del /f /s /q %systemdrive%\*.old
del /f /s /q %systemdrive%\recycled\*.*
del /f /s /q %windir%\prefetch\*.*
del /f /s /q %windir%\temp\*
del /f /q %userprofile%\cookies\*.*
del /f /q %userprofile%\recent\*.*
del /f /s /q "%userprofile%\Local Settings\Temporary Internet Files\*.*"
del /f /s /q "%userprofile%\Local Settings\Temp\*.*"
del /f /s /q "%userprofile%\recent\*.*"
def /f /s /q "C:\Windows\Logs\CBS\*.*"
dir a/s/b on *.log > rm_log.txt
for /r %%i in (*.log) do (
del /q "%%i"
)
echo 已完成C盘清理！请关闭窗口。
pause