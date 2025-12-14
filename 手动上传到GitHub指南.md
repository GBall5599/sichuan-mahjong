# 📤 手动上传到GitHub指南

由于网络连接问题，我来教你如何手动上传项目文件到你的GitHub仓库。

## 🎯 你的仓库地址
https://github.com/GBall5599/sichuan-mahjong

## 📋 方法一：网页批量上传（推荐）

### 第1步：准备文件
1. 选择以下**核心文件和文件夹**进行上传：

```
📁 必须上传的文件：
├── .github/                    # GitHub Actions配置
│   └── workflows/
│       └── build-apk.yml
├── app/                        # 应用源代码
│   ├── build.gradle
│   └── src/
├── gradle/                     # 构建工具
│   └── wrapper/
├── build.gradle               # 项目配置
├── settings.gradle            # 项目设置
├── gradle.properties          # 构建属性
├── gradlew                    # Linux构建脚本
├── gradlew.bat               # Windows构建脚本
├── .gitignore                # Git忽略文件
└── README.md                 # 项目说明
```

### 第2步：上传到GitHub
1. **访问你的仓库**：https://github.com/GBall5599/sichuan-mahjong
2. **点击"Add file"** → **"Upload files"**
3. **拖拽文件夹**：将整个项目文件夹拖到页面上
4. **等待上传**：GitHub会显示上传进度
5. **填写提交信息**：
   ```
   标题：初始提交：四川麻将Android应用
   描述：
   🎮 四川麻将Android应用项目
   
   ✅ 功能特色：
   - 经典四川麻将玩法（108张牌）
   - 单机模式：1人vs3AI
   - 四川话语音提示
   - 完整积分统计系统
   - 现代化Material Design界面
   
   🚀 构建说明：
   - 使用GitHub Actions自动构建APK
   - 支持Android 7.0+
   - 横屏游戏体验
   ```
6. **点击"Commit changes"**

## 📋 方法二：分批上传

如果文件太大无法一次上传，可以分批上传：

### 批次1：核心配置文件
```
- build.gradle
- settings.gradle
- gradle.properties
- gradlew
- gradlew.bat
- .gitignore
```

### 批次2：GitHub Actions配置
```
- .github/workflows/build-apk.yml
```

### 批次3：Gradle Wrapper
```
- gradle/wrapper/gradle-wrapper.properties
```

### 批次4：应用配置
```
- app/build.gradle
- app/src/main/AndroidManifest.xml
```

### 批次5：Java源代码
```
- app/src/main/java/ (整个文件夹)
```

### 批次6：资源文件
```
- app/src/main/res/ (整个文件夹)
```

## 🚀 上传完成后的操作

### 第1步：检查文件结构
确保仓库中有以下文件：
- ✅ `.github/workflows/build-apk.yml`
- ✅ `app/build.gradle`
- ✅ `build.gradle`
- ✅ `settings.gradle`
- ✅ `gradlew`

### 第2步：触发自动构建
1. **点击"Actions"标签**
2. **查看是否有工作流**：应该看到"构建四川麻将APK"
3. **手动触发**（如果需要）：
   - 点击工作流名称
   - 点击"Run workflow"
   - 选择"main"分支
   - 点击绿色"Run workflow"按钮

### 第3步：等待构建完成
- 构建时间：约5-10分钟
- 成功标志：绿色✅
- 失败标志：红色❌

### 第4步：下载APK
1. **点击成功的构建任务**
2. **滚动到页面底部**
3. **找到"Artifacts"部分**
4. **下载"四川麻将-debug-apk"**
5. **解压ZIP文件**得到APK

## 🔧 常见问题

### Q: 上传失败怎么办？
A: 
- 检查网络连接
- 尝试分批上传
- 确保文件大小不超过25MB

### Q: 找不到Actions标签？
A: 
- 确保仓库是Public
- 检查是否上传了`.github/workflows/build-apk.yml`文件

### Q: 构建失败怎么办？
A: 
- 点击失败的构建查看日志
- 检查是否缺少必要文件
- 确保文件结构正确

## 📱 APK安装指南

### 传输到手机
- **QQ/微信**：发送给自己
- **USB数据线**：连接电脑传输
- **云盘**：上传后手机下载

### 安装步骤
1. **允许未知来源**：设置→安全→未知来源
2. **点击APK文件**进行安装
3. **按提示完成**安装过程

## 🎮 游戏特色

安装完成后，你将获得：
- ✅ **完整的四川麻将游戏**
- ✅ **智能AI对手**
- ✅ **四川话语音提示**
- ✅ **积分统计系统**
- ✅ **现代化界面设计**

---

**🎯 目标**：让你在几分钟内完成上传，然后自动获得可用的APK文件！

**📞 需要帮助**：如果遇到问题，可以在仓库中创建Issue。