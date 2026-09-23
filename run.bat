@echo off
chcp 65001 > nul
echo Dang bien dich ma nguon...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin -sourcepath src src/model/*.java src/exception/*.java src/util/*.java src/repository/*.java src/service/*.java src/view/*.java src/controller/*.java src/App.java
if %errorlevel% neq 0 (
    echo [LOI] Bien dich that bai!
    pause
    exit /b %errorlevel%
)
echo Bien dich thanh cong! Khoi chay ung dung Quan ly Benh an...
echo.
java -Dfile.encoding=UTF-8 -cp bin App
pause
