package com.sichuanmahjong.game.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 积分记录实体
 */
@Entity(tableName = "score_records")
data class ScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val gameDate: Long,
    val playerScore: Int,
    val isWin: Boolean,
    val gameType: String = "四川麻将",
    val gameDuration: Long = 0 // 游戏时长（秒）
)