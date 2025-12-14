# 🚀 GitHub在线构建APK详细指南

## 📋 完整步骤（小白版）

### 第一步：注册GitHub账号

1. **访问GitHub官网**
   - 打开浏览器，访问：https://github.com
   - 点击右上角"Sign up"按钮

2. **填写注册信息**
   - 用户名：选择一个唯一的用户名
   - 邮箱：填写你的邮箱地址
   - 密码：设置一个强密码
   - 完成人机验证

3. **验证邮箱**
   - 查收邮箱中的验证邮件
   - 点击邮件中的验证链接

### 第二步：创建新仓库

1. **创建仓库**
   - 登录GitHub后，点击右上角"+"号
   - 选择"New repository"
   - 仓库名称输入：`sichuan-mahjong`
   - 描述输入：`四川麻将Android应用`
   - 选择"Public"（公开）
   - 勾选"Add a README file"
   - 点击"Create repository"

### 第三步：上传项目文件

#### 方法A：网页上传（推荐新手）

1. **准备文件**
   - 将整个项目文件夹压缩成ZIP文件
   - 或者逐个上传重要文件

2. **上传步骤**
   - 在仓库页面点击"uploading an existing file"
   - 拖拽文件或点击"choose your files"
   - 选择项目中的所有文件（除了.git文件夹）
   - 在底部填写提交信息："初始提交：四川麻将项目"
   - 点击"Commit changes"

#### 方法B：使用Git命令（进阶）

```bash
# 1. 克隆仓库到本地
git clone https://github.com/你的用户名/sichuan-mahjong.git

# 2. 进入仓库目录
cd sichuan-mahjong

# 3. 复制项目文件到此目录

# 4. 添加所有文件
git add .

# 5. 提交更改
git commit -m "初始提交：四川麻将项目"

# 6. 推送到GitHub
git push origin main
```

### 第四步：触发自动构建

1. **检查Actions是否启用**
   - 在仓库页面点击"Actions"标签
   - 如果看到"Get started with GitHub Actions"，说明需要启用
   - 如果看到工作流列表，说明已启用

2. **手动触发构建**
   - 点击"Actions"标签
   - 选择"构建四川麻将APK"工作流
   - 点击"Run workflow"按钮
   - 选择"main"分支
   - 点击绿色的"Run workflow"按钮

3. **等待构建完成**
   - 构建过程大约需要5-10分钟
   - 可以点击正在运行的工作流查看实时日志
   - 绿色✅表示成功，红色❌表示失败

### 第五步：下载APK文件

1. **构建成功后**
   - 在Actions页面找到成功的构建
   - 点击构建名称进入详情页
   - 在页面底部找到"Artifacts"部分
   - 点击"四川麻将-debug-apk"下载ZIP文件

2. **解压获取APK**
   - 下载的是ZIP文件，需要解压
   - 解压后得到`app-debug.apk`文件
   - 这就是可以安装的APK文件

## 📱 APK安装到手机

### 传输APK到手机
- **方法1**: USB数据线连接电脑，复制APK到手机
- **方法2**: 通过QQ、微信等发送给自己
- **方法3**: 上传到网盘，手机下载

### 安装APK
1. **允许未知来源**
   - 设置 → 安全 → 未知来源（开启）
   - 或在安装时选择"仍要安装"

2. **安装应用**
   - 在手机上找到APK文件
   - 点击APK文件
   - 按提示完成安装

## 🔧 常见问题解决

### 构建失败怎么办？

1. **检查文件结构**
   ```
   确保上传了以下关键文件：
   ├── app/
   │   ├── src/
   │   └── build.gradle
   ├── gradle/
   │   └── wrapper/
   ├── .github/
   │   └── workflows/
   │       └── build-apk.yml
   ├── build.gradle
   ├── settings.gradle
   └── gradlew
   ```

2. **查看错误日志**
   - 点击失败的构建
   - 查看红色的错误信息
   - 根据错误信息修复问题

3. **常见错误修复**
   - **找不到gradlew**: 确保上传了gradlew文件
   - **权限错误**: 检查.github/workflows/build-apk.yml文件
   - **依赖错误**: 检查build.gradle文件配置

### 无法下载APK？

1. **检查构建状态**
   - 确保构建显示绿色✅
   - 等待构建完全完成

2. **查找Artifacts**
   - 在构建详情页面底部
   - 如果没有Artifacts，说明构建失败

## 🎯 优化建议

### 自动发布Release
当你想发布正式版本时：
1. 在仓库页面点击"Releases"
2. 点击"Create a new release"
3. 标签版本：v1.0.0
4. 发布标题：四川麻将 v1.0.0
5. 描述游戏特色和更新内容
6. 点击"Publish release"

### 设置构建徽章
在README.md中添加构建状态徽章：
```markdown
![构建状态](https://github.com/你的用户名/sichuan-mahjong/workflows/构建四川麻将APK/badge.svg)
```

## 📞 需要帮助？

如果遇到问题：
1. 检查GitHub Actions的日志
2. 确认所有文件都已正确上传
3. 参考本指南重新操作
4. 在GitHub仓库中创建Issue求助

---

**提示**: GitHub Actions每月有2000分钟的免费构建时间，对于个人项目完全够用。构建一次大约消耗5-10分钟。