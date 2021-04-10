set projectLocation=C:\Users\500068426\eclipse-workspace\TA_project
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause