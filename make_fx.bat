REM Some batch file commands for Win10 users to try
REM replace with path to your javac,java,jar,javafx installations

SET JDK=C:\Program Files\Java\jdk-11.0.6\bin
SET JRE=C:\Program Files\Java\jre-10\bin
SET JC="%JDK%javac.exe" 
SET JAVA="%JRE%javaw.exe" 
SET JAR="%JRE%jar.exe" 
SET MP=--module-path javafx-sdk-11.0.2/lib 
SET AM=--add-modules javafx.controls,javafx.fxml -Dfile.encoding=UTF-8 
SET CP=-classpath ".application" 
SET APP=application.Main

REM Uncomment next line to compile JavaFX 
JC MP AM CP -d . application\*.java

REM Uncomment next line to run JavaFX
%JAVA% %MP% %AM% %CP% %APP%

REM Uncomment next line to build executable jar file
%JAR% cvmf manifest.txt executable.jar .

REM Uncomment next line to run executable jar file
%JAVA% %MP% -jar executable.jar

REM Uncomment next line to create zip file using jar 
%JAR% team.zip application\* *

