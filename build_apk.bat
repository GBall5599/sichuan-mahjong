@echo off
echo 正在构建四川麻将APK...
echo.

REM 检查是否安装了Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo 错误: 未找到Java，请先安装Java 8或更高版本
    pause
    exit /b 1
)

REM 检查是否存在gradlew
if not exist "gradlew.bat" (
    echo 错误: 未找到gradlew.bat文件
    pause
    exit /b 1
)

echo 开始构建APK...
call gradlew.bat assembleDebug

if %errorlevel% equ 0 (
    echo.
    echo ✅ APK构建成功！
    echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 你可以将此APK文件安装到Android设备上
) else (
    echo.
    echo ❌ APK构建失败，请检查错误信息
)

pause