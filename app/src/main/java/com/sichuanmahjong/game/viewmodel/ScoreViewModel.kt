package com.sichuanmahjong.game.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.sichuanmahjong.game.database.AppDatabase
import com.sichuanmahjong.game.database.ScoreEntity
import kotlinx.coroutines.launch

/**
 * 积分管理ViewModel
 */
class ScoreViewModel(application: Application) : AndroidViewModel(application) {
    
    private val scoreDao = AppDatabase.getDatabase(application).scoreDao()
    
    val allScores: LiveData<List<ScoreEntity>> = scoreDao.getAllScores()
    val currentScore: LiveData<Int?> = scoreDao.getTotalScore()
    val totalGames: LiveData<Int> = scoreDao.getTotalGames()
    val winCount: LiveData<Int> = scoreDao.getWinCount()
    val recentScores: LiveData<List<ScoreEntity>> = scoreDao.getRecentScores()
    val averageScore: LiveData<Float?> = scoreDao.getAverageScore()
    val highestScore: LiveData<Int?> = scoreDao.getHighestScore()
    
    // 计算胜率
    val winRate: LiveData<Float> = MediatorLiveData<Float>().apply {
        var totalGamesValue = 0
        var winCountValue = 0
        
        addSource(totalGames) { total ->
            totalGamesValue = total
            value = if (totalGamesValue > 0) {
                (winCountValue.toFloat() / totalGamesValue) * 100
            } else {
                0f
            }
        }
        
        addSource(winCount) { wins ->
            winCountValue = wins
            value = if (totalGamesValue > 0) {
                (winCountValue.toFloat() / totalGamesValue) * 100
            } else {
                0f
            }
        }
    }
    
    /**
     * 添加游戏记录
     */
    fun addGameRecord(score: Int, isWin: Boolean, gameDuration: Long = 0) {
        viewModelScope.launch {
            val scoreEntity = ScoreEntity(
                gameDate = System.currentTimeMillis(),
                playerScore = score,
                isWin = isWin,
                gameDuration = gameDuration
            )
            scoreDao.insertScore(scoreEntity)
        }
    }
    
    /**
     * 删除游戏记录
     */
    fun deleteGameRecord(scoreEntity: ScoreEntity) {
        viewModelScope.launch {
            scoreDao.deleteScore(scoreEntity)
        }
    }
    
    /**
     * 重置所有积分
     */
    fun resetAllScores() {
        viewModelScope.launch {
            scoreDao.deleteAllScores()
        }
    }
}