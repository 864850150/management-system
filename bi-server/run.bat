@ECHO OFF
@ECHO STARTUP App
@echo ��ǰĿ¼ %cd%
@ECHO ���û�������,ѭ����ǰĿ¼�µ�libĿ¼������jar�ļ�,������CLASSPATH
FOR %%F IN (%cd%\lib\*.jar) DO call :addcp %%F
goto extlibe
:addcp
SET CLASSPATH=%CLASSPATH%;%1
goto :eof
:extlibe
@ECHO ��ʾCLASSPATH
SET CLASSPATH 
@ECHO ����Ӧ�ó���
java -Dport=10007 -Dntport=8787 com.ruyu.web.portal.bi.BiServer
pause