@Echo Off
D:
cd D:\clouds\tools\codetools\frp_windows_amd64

start frpc.exe -c frpc.ini

::保留cmd窗口 不需要时可注释
@Echo on