@Echo off

:function
set /p a=��ѡ��1,2,3
if "%a%"=="1" Goto function1
if "%a%"=="2" Goto function2
if "%a%"=="3" Goto function3

:function1
Echo ��ѡ����1
goto function

:function2
Echo ��ѡ����2
goto function

:function3
Echo ��ѡ����3
goto end

:end
Echo ��ѡ����3,����
%��ͣ%
pause
@Echo on