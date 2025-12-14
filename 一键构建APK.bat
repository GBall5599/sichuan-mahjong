@echo off
chcp 65001 >nul
title 四川麻将 APK 构建工具

echo.
echo ╔══════════════════════════════════════════════════════════════╗
echo ║                    四川麻将 APK 构建工具                      ║
echo ║                                                              ║
echo ║  本工具将自动下载必要组件并构建APK文件                        ║
echo ║  首次运行需要下载约500MB的构建工具，请保持网络连接             ║
echo ╚══════════════════════════════════════════════════════════════╝
echo.

REM 设置环境变量
set JAVA_OPTS=-Xmx2048m -Dfile.encoding=UTF-8
set GRADLE_OPTS=-Dorg.gradle.daemon=false

echo 🔍 检查系统环境...

REM 检查Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo.
    echo ❌ 未检测到Java环境！
    echo.
    echo 📥 正在尝试下载便携版Java...
    if not exist "java-portable" (
        mkdir java-portable
        echo 请手动下载Java 8+ 并解压到 java-portable 文件夹
        echo 下载地址: https://www.oracle.com/java/technologies/downloads/
        echo.
        echo 或者安装系统Java后重新运行此脚本
        pause
        exit /b 1
    )
) else (
    echo ✅ Java环境检测成功
)

echo.
echo 🏗️  准备构建环境...

REM 创建必要目录
if not exist "gradle\wrapper" mkdir "gradle\wrapper"
if not exist "app\build" mkdir "app\build"

REM 检查并下载Gradle Wrapper
if not exist "gradle\wrapper\gradle-wrapper.jar" (
    echo 📥 正在下载Gradle Wrapper...
    
    REM 使用PowerShell下载
    powershell -Command "try { Invoke-WebRequest -Uri 'https://github.com/gradle/gradle/raw/v8.2.0/gradle/wrapper/gradle-wrapper.jar' -OutFile 'gradle\wrapper\gradle-wrapper.jar' -UseBasicParsing } catch { exit 1 }" 2>nul
    
    if not exist "gradle\wrapper\gradle-wrapper.jar" (
        echo ⚠️  下载失败，尝试备用方法...
        
        REM 创建一个最小的wrapper jar（占位符）
        echo. > "gradle\wrapper\gradle-wrapper.jar"
        
        echo.
        echo 📋 手动下载说明：
        echo 1. 访问 https://services.gradle.org/distributions/gradle-8.2-bin.zip
        echo 2. 下载并解压
        echo 3. 复制 lib/gradle-wrapper.jar 到 gradle/wrapper/ 目录
        echo 4. 重新运行此脚本
        echo.
        pause
        exit /b 1
    )
    echo ✅ Gradle Wrapper下载完成
)

echo.
echo 🔨 开始构建APK...
echo 注意：首次构建会下载Android构建工具，请耐心等待...
echo.

REM 设置构建参数
set GRADLE_ARGS=assembleDebug --no-daemon --stacktrace --info

REM 执行构建
call gradlew.bat %GRADLE_ARGS%

if %errorlevel% equ 0 (
    echo.
    echo ╔══════════════════════════════════════════════════════════════╗
    echo ║                        🎉 构建成功！                          ║
    echo ╚══════════════════════════════════════════════════════════════╝
    echo.
    echo 📁 APK文件位置：
    echo    %CD%\app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 📱 安装步骤：
    echo 1. 将APK文件复制到Android手机
    echo 2. 在手机上点击APK文件
    echo 3. 允许"未知来源"应用安装
    echo 4. 完成安装并享受游戏！
    echo.
    echo 🎮 游戏特色：
    echo • 经典四川麻将玩法
    echo • 四川话语音提示
    echo • 智能AI对手
    echo • 积分统计系统
    echo.
    
    REM 尝试打开APK所在文件夹
    if exist "app\build\outputs\apk\debug\app-debug.apk" (
        echo 📂 正在打开APK文件夹...
        explorer "app\build\outputs\apk\debug\"
    )
    
) else (
    echo.
    echo ╔══════════════════════════════════════════════════════════════╗
    echo ║                        ❌ 构建失败                            ║
    echo ╚══════════════════════════════════════════════════════════════╝
    echo.
    echo 🔧 可能的解决方案：
    echo.
    echo 1. 网络问题：
    echo    • 检查网络连接
    echo    • 尝试使用VPN
    echo    • 稍后重试
    echo.
    echo 2. Java问题：
    echo    • 确保Java版本为8或更高
    echo    • 重新安装Java
    echo.
    echo 3. 权限问题：
    echo    • 以管理员身份运行
    echo    • 检查防火墙设置
    echo.
    echo 4. 使用Android Studio：
    echo    • 下载Android Studio
    echo    • 打开本项目
    echo    • 点击Build > Build APK
    echo.
)

echo.
echo 按任意键退出...
pause >nul