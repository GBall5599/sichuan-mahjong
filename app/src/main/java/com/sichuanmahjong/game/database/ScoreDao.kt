package com.sichuanmahjong.game.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * 积分数据访问对象
 */
@Dao
interface ScoreDao {
    
    @Query("SELECT * FROM score_records ORDER BY gameDate DESC")
    fun getAllScores(): LiveData<List<ScoreEntity>>
    
    @Query("SELECT SUM(playerScore) FROM score_records")
    fun getTotalScore(): LiveData<Int?>
    
    @Query("SELECT COUNT(*) FROM score_records")
    fun getTotalGames(): LiveData<Int>
    
    @Query("SELECT COUNT(*) FROM score_records WHERE isWin = 1")
    fun getWinCount(): LiveData<Int>
    
    @Query("SELECT * FROM score_records ORDER BY gameDate DESC LIMIT 10")
    fun getRecentScores(): LiveData<List<ScoreEntity>>
    
    @Insert
    suspend fun insertScore(score: ScoreEntity)
    
    @Delete
    suspend fun deleteScore(score: ScoreEntity)
    
    @Query("DELETE FROM score_records")
    suspend fun deleteAllScores()
    
    @Query("SELECT AVG(CAST(playerScore AS FLOAT)) FROM score_records")
    fun getAverageScore(): LiveData<Float?>
    
    @Query("SELECT MAX(playerScore) FROM score_records")
    fun getHighestScore(): LiveData<Int?>
}