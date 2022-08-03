@Echo Off
::将本地文件上传到ftp
::地址
set ip=122.52.168.29
::账号
set user=icbcmon
::密码
set pwd=icbcmon789#

::设置ftp信息  创建ftp临时文件
Echo open %ip%>ftp.up
Echo %user%>>ftp.up
Echo %pwd%>>ftp.up

::进入ftp路径
Echo cd /home >>ftp.up
::上传ftp文件
Echo put "D:\ftp\1.txt" >>ftp.up

::退出ftp
Echo bye>>ftp.up
::清除ftp缓存文件
FTP -s:ftp.up
::保留cmd窗口 不需要时可注释
pause
@Echo on