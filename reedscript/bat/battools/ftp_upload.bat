@Echo Off
::�������ļ��ϴ���ftp
::��ַ
set ip=122.52.168.29
::�˺�
set user=icbcmon
::����
set pwd=icbcmon789#

::����ftp��Ϣ  ����ftp��ʱ�ļ�
Echo open %ip%>ftp.up
Echo %user%>>ftp.up
Echo %pwd%>>ftp.up

::����ftp·��
Echo cd /home >>ftp.up
::�ϴ�ftp�ļ�
Echo put "D:\ftp\1.txt" >>ftp.up

::�˳�ftp
Echo bye>>ftp.up
::���ftp�����ļ�
FTP -s:ftp.up
::����cmd���� ����Ҫʱ��ע��
pause
@Echo on