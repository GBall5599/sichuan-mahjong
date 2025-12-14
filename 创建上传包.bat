@echo off
chcp 65001 >nul
echo ==========================================
echo        创建GitHub上传包
echo ==========================================
echo.

echo 📦 正在创建上传包...

REM 创建临时目录
if exist "github-upload" rmdir /s /q "github-upload"
mkdir "github-upload"

echo 📁 复制核心文件...

REM 复制核心配置文件
copy "build.gradle" "github-upload\" >nul 2>&1
copy "settings.gradle" "github-upload\" >nul 2>&1
copy "gradle.properties" "github-upload\" >nul 2>&1
copy "gradlew" "github-upload\" >nul 2>&1
copy "gradlew.bat" "github-upload\" >nul 2>&1
copy ".gitignore" "github-upload\" >nul 2>&1
copy "README.md" "github-upload\" >nul 2>&1

REM 复制GitHub Actions配置
if not exist "github-upload\.github" mkdir "github-upload\.github"
if not exist "github-upload\.github\workflows" mkdir "github-upload\.github\workflows"
copy ".github\workflows\build-apk.yml" "github-upload\.github\workflows\" >nul 2>&1

REM 复制Gradle Wrapper
if not exist "github-upload\gradle" mkdir "github-upload\gradle"
if not exist "github-upload\gradle\wrapper" mkdir "github-upload\gradle\wrapper"
copy "gradle\wrapper\gradle-wrapper.properties" "github-upload\gradle\wrapper\" >nul 2>&1

REM 复制app目录
echo 📱 复制应用文件...
xcopy "app" "github-upload\app" /E /I /Q >nul 2>&1

echo.
echo ✅ 上传包创建完成！
echo.
echo 📁 上传包位置：github-upload 文件夹
echo.
echo 📋 接下来的步骤：
echo 1. 打开 github-upload 文件夹
echo 2. 选择所有文件和文件夹
echo 3. 访问：https://github.com/GBall5599/sichuan-mahjong
echo 4. 点击 "Add file" → "Upload files"
echo 5. 拖拽所有文件到页面上
echo 6. 填写提交信息并提交
echo.
echo 🚀 上传完成后，GitHub会自动构建APK！
echo.

REM 打开文件夹
explorer "github-upload"

pause