@Echo off

:function
set /p a=请选择1,2,3
if "%a%"=="1" Goto function1
if "%a%"=="2" Goto function2
if "%a%"=="3" Goto function3

:function1
Echo 你选择了1
goto function

:function2
Echo 你选择了2
goto function

:function3
Echo 你选择了3
goto end

:end
Echo 你选择了3,结束
%暂停%
pause
@Echo on