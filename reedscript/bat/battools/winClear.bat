@echo off

::ɾ���ļ�����
::del /f /s /q D:\cache\1.txt
::del /f /s /q D:\cache\reedtools\*.*

::ɾ���ļ��а���
::rd /s /q D:\cache\path

::ϵͳ���»ص��ļ���
del /f /s /q  C:\Windows\servicing\LCU\*.*
::����ϵͳ����֮��Ļ����ļ���
del /f /s /q  C:\Windows\SoftwareDistribution\Download\*.*
::ϵͳ������ʷ���̼�¼
del /f /s /q  C:\Windows\Logs\CBS\*.*
del /f /s /q  C:\Users\Public\Documents\*.*
::����������־
del /f /s /q  C:\Users\reedbook\AppData\Roaming\eCloudUpgrade\*.*
del /f /s /q  C:\Users\reedbook\AppData\Roaming\ecloud\logs\*.*
del /f /s /q  C:\Users\reedbook\AppData\Roaming\ecloud\Dump\*.*

::edge
del /f /s /q  "C:\Program Files (x86)\Microsoft\EdgeUpdate\Download\*.*"

::˼Դ�ʼ�
del /f /s /q  C:\Users\reedbook\AppData\Local\siyuan-updater

::JetBrains Toolbox����
del /f /s /q  C:\Users\reedbook\AppData\Local\JetBrains\Toolbox\download\*.*

@Echo on
pause
