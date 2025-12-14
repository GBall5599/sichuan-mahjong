@echo off
chcp 65001 >nul
echo ==========================================
echo          四川麻将 APK 构建工具
echo ==========================================
echo.

echo 📱 正在准备构建环境...

REM 检查Java
echo 🔍 检查Java环境...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ 未找到Java！
    echo.
    echo 请先安装Java：
    echo 1. 访问 https://www.oracle.com/java/technologies/downloads/
    echo 2. 下载并安装Java 8或更高版本
    echo 3. 重新运行此脚本
    echo.
    pause
    exit /b 1
)
echo ✅ Java环境正常

echo.
echo 🏗️  开始构建APK...
echo 注意：首次构建会下载依赖文件，请保持网络连接
echo.

REM 创建必要的目录
if not exist "gradle\wrapper" mkdir "gradle\wrapper"

REM 下载Gradle Wrapper（如果不存在）
if not exist "gradle\wrapper\gradle-wrapper.jar" (
    echo 📥 正在下载Gradle Wrapper...
    powershell -Command "& {Invoke-WebRequest -Uri 'https://github.com/gradle/gradle/raw/v8.2.0/gradle/wrapper/gradle-wrapper.jar' -OutFile 'gradle\wrapper\gradle-wrapper.jar'}" 2>nul
    if %errorlevel% neq 0 (
        echo ⚠️  自动下载失败，请手动下载gradle-wrapper.jar
        echo 或使用Android Studio打开项目进行构建
        pause
        exit /b 1
    )
)

REM 构建APK
echo 🔨 正在编译项目...
call gradlew.bat assembleDebug --no-daemon --stacktrace

if %errorlevel% equ 0 (
    echo.
    echo ==========================================
    echo ✅ APK构建成功！
    echo ==========================================
    echo.
    echo 📁 APK文件位置：
    echo    app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 📱 安装方法：
    echo 1. 将APK文件传输到Android手机
    echo 2. 在手机上点击APK文件
    echo 3. 允许安装未知来源应用
    echo 4. 完成安装
    echo.
    echo 🎮 系统要求：Android 7.0或更高版本
    echo.
) else (
    echo.
    echo ==========================================
    echo ❌ 构建失败
    echo ==========================================
    echo.
    echo 可能的解决方案：
    echo 1. 检查网络连接
    echo 2. 确保Java版本正确
    echo 3. 尝试使用Android Studio构建
    echo.
)

echo 按任意键退出...
pause >nul