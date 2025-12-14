# 四川麻将 Android App

一个支持单机模式的四川麻将安卓应用，包含四川话语音和积分统计功能。

## 功能特点

### 🎮 游戏功能
- **单机模式**: 支持1个玩家对战3个AI电脑
- **四川麻将规则**: 采用经典四川麻将玩法（108张牌）
- **智能AI**: 电脑玩家具备基本的麻将AI逻辑

### 🔊 语音功能
- **四川话语音**: 支持胡、碰、杠等操作的四川话语音提示
- **语音管理**: 可调节音量，支持静音模式
- **备用TTS**: 当语音文件缺失时自动使用系统文字转语音

### 📊 积分统计
- **实时积分**: 显示当前总积分
- **游戏统计**: 记录总局数、胜率、平均得分
- **历史记录**: 详细的游戏记录，包含时间、得分、胜负状态
- **数据管理**: 支持重置积分记录

## 技术架构

### 开发环境
- **语言**: Kotlin
- **最低SDK**: Android 7.0 (API 24)
- **目标SDK**: Android 14 (API 34)
- **架构**: MVVM + LiveData + Room

### 主要技术栈
- **UI框架**: Android View + ViewBinding
- **数据库**: Room Database
- **架构组件**: ViewModel, LiveData
- **音频**: MediaPlayer + TextToSpeech
- **界面**: Material Design Components

### 项目结构
```
app/src/main/java/com/sichuanmahjong/game/
├── MainActivity.kt              # 主界面
├── GameActivity.kt             # 游戏界面
├── ScoreActivity.kt            # 积分记录界面
├── model/                      # 数据模型
│   ├── MahjongTile.kt         # 麻将牌模型
│   └── Player.kt              # 玩家模型
├── viewmodel/                  # ViewModel层
│   ├── GameViewModel.kt       # 游戏逻辑
│   └── ScoreViewModel.kt      # 积分管理
├── database/                   # 数据库层
│   ├── ScoreEntity.kt         # 积分实体
│   ├── ScoreDao.kt            # 数据访问对象
│   └── AppDatabase.kt         # 数据库配置
├── adapter/                    # 适配器
│   └── ScoreAdapter.kt        # 积分记录适配器
└── utils/                      # 工具类
    └── SichuanVoiceManager.kt # 四川话语音管理
```

## 安装说明

### 开发环境要求
1. Android Studio Arctic Fox 或更高版本
2. JDK 8 或更高版本
3. Android SDK 24+

### 构建步骤
1. 克隆项目到本地
2. 使用Android Studio打开项目
3. 等待Gradle同步完成
4. 连接Android设备或启动模拟器
5. 点击运行按钮构建并安装应用

### 语音文件配置
将四川话语音文件放置在 `app/src/main/res/raw/` 目录下：
- `sichuan_hu.mp3` - 胡牌语音
- `sichuan_peng.mp3` - 碰牌语音
- `sichuan_gang.mp3` - 杠牌语音
- `sichuan_chi.mp3` - 吃牌语音

## 游戏规则

### 四川麻将特点
- 使用万、条、筒三种花色，每种1-9各4张，共108张牌
- 不使用风牌和箭牌
- 支持碰、杠操作
- 简化的胡牌判断逻辑

### 积分规则
- 胡牌：+10分
- 杠牌：+2分
- 失败：0分或负分

## 后续开发计划

### 功能增强
- [ ] 更完善的四川麻将规则实现
- [ ] 更智能的AI算法
- [ ] 更多四川话语音内容
- [ ] 游戏设置界面
- [ ] 牌桌动画效果

### 技术优化
- [ ] 性能优化
- [ ] UI/UX改进
- [ ] 代码重构
- [ ] 单元测试

## 许可证

本项目仅供学习和个人使用。

## 联系方式

如有问题或建议，欢迎提交Issue或Pull Request。