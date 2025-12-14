# 📱 四川麻将APK下载指南

## 🎉 好消息：构建成功了！

从你遇到的错误信息可以看出，GitHub Actions已经成功构建了APK，只是在下载时遇到了文件名编码问题。

## 🔧 问题分析

**错误原因**：
```xml
<QueryParameterValue>attachment; filename="四川麻将-debug-apk.zip"</QueryParameterValue>
<Reason>HTTP query parameter values contain invalid characters</Reason>
```

**问题**：中文文件名在HTTP下载时导致编码问题

**解决方案**：我已经修复了GitHub Actions配置，使用英文文件名

## 📥 APK下载方法

### 方法1：等待新构建（推荐）

1. **等待新构建完成**
   - 我刚刚推送了修复，GitHub会自动开始新的构建
   - 新的构建使用英文文件名：`sichuan-mahjong-debug-apk`

2. **下载步骤**
   - 访问：https://github.com/GBall5599/sichuan-mahjong/actions
   - 点击最新的成功构建（绿色✅）
   - 在页面底部找到"Artifacts"
   - 下载"sichuan-mahjong-debug-apk"

### 方法2：直接获取当前APK

如果你想立即获取APK，可以尝试以下方法：

1. **查看构建日志**
   - 在GitHub Actions页面点击成功的构建
   - 查看"上传APK文件"步骤
   - 确认APK已成功生成

2. **使用GitHub CLI（如果安装了）**
   ```bash
   gh run download [run-id] --name sichuan-mahjong-debug-apk
   ```

3. **浏览器直接访问**
   - 有时刷新页面或使用不同浏览器可以解决下载问题

## 🎮 APK安装指南

### 传输到手机
- **QQ/微信**：发送给自己（推荐）
- **USB数据线**：连接电脑传输
- **云盘**：上传到百度云盘/阿里云盘等

### 安装步骤
1. **允许未知来源**
   - 设置 → 安全 → 未知来源（开启）
   - 或在安装时选择"仍要安装"

2. **安装APK**
   - 在手机上点击APK文件
   - 按提示完成安装
   - 在桌面找到"四川麻将"图标

## 🎯 游戏特色

安装后你将获得：

### 核心功能
- ✅ **经典四川麻将**：万条筒三色，108张牌
- ✅ **单机模式**：1个玩家对战3个智能AI
- ✅ **四川话语音**：胡碰杠等操作的TTS语音提示
- ✅ **积分统计**：完整的游戏记录和统计系统

### 界面特色
- ✅ **横屏体验**：专为手机游戏优化
- ✅ **Material Design**：现代化的Android界面
- ✅ **专业图标**：四川麻将主题的应用图标
- ✅ **兼容性强**：支持Android 7.0到最新版本

## 📞 如果仍有问题

### 下载问题
1. **刷新页面**：有时浏览器缓存导致问题
2. **换浏览器**：尝试Chrome、Firefox等
3. **等待新构建**：使用修复后的英文文件名

### 安装问题
1. **检查Android版本**：需要7.0或更高
2. **允许未知来源**：在设置中开启
3. **清理存储空间**：确保有足够空间

## 🚀 构建状态

- **当前状态**：APK构建成功，文件名已修复
- **仓库地址**：https://github.com/GBall5599/sichuan-mahjong
- **Actions页面**：https://github.com/GBall5599/sichuan-mahjong/actions
- **预计时间**：新构建约3-5分钟完成

---

**🎉 恭喜**：你的四川麻将应用已经成功构建！只需要下载并安装到手机即可开始游戏。

**📱 提示**：建议等待新的构建完成（使用英文文件名），这样下载会更顺利。