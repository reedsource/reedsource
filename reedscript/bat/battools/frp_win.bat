@Echo Off
:: 注意 脚本需要远程服务器frps启动情况下启动,否则报错
D:
cd D:\clouds\tools\codetools\frp_windows_amd64

start frpc.exe -c frpc.ini
@Echo on