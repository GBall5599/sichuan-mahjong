package com.sichuanmahjong.game

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sichuanmahjong.game.adapter.ScoreAdapter
import com.sichuanmahjong.game.databinding.ActivityScoreBinding
import com.sichuanmahjong.game.viewmodel.ScoreViewModel
import java.text.SimpleDateFormat
import java.util.*

class ScoreActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityScoreBinding
    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var scoreAdapter: ScoreAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        scoreViewModel = ViewModelProvider(this)[ScoreViewModel::class.java]
        
        setupRecyclerView()
        setupUI()
        observeScores()
    }
    
    private fun setupRecyclerView() {
        scoreAdapter = ScoreAdapter { scoreEntity ->
            // 点击删除记录
            AlertDialog.Builder(this)
                .setTitle("删除记录")
                .setMessage("确定要删除这条记录吗？")
                .setPositiveButton("删除") { _, _ ->
                    scoreViewModel.deleteGameRecord(scoreEntity)
                }
                .setNegativeButton("取消", null)
                .show()
        }
        
        binding.recyclerViewScores.apply {
            layoutManager = LinearLayoutManager(this@ScoreActivity)
            adapter = scoreAdapter
        }
    }
    
    private fun setupUI() {
        binding.btnResetScores.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("重置积分")
                .setMessage("确定要重置所有积分记录吗？此操作不可恢复。")
                .setPositiveButton("重置") { _, _ ->
                    scoreViewModel.resetAllScores()
                }
                .setNegativeButton("取消", null)
                .show()
        }
        
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
    
    private fun observeScores() {
        scoreViewModel.currentScore.observe(this) { score ->
            binding.tvTotalScore.text = "总积分: ${score ?: 0}"
        }
        
        scoreViewModel.totalGames.observe(this) { games ->
            binding.tvTotalGames.text = "总局数: $games"
        }
        
        scoreViewModel.winRate.observe(this) { rate ->
            binding.tvWinRate.text = "胜率: ${String.format("%.1f", rate)}%"
        }
        
        scoreViewModel.averageScore.observe(this) { avg ->
            binding.tvAverageScore.text = "平均得分: ${String.format("%.1f", avg ?: 0f)}"
        }
        
        scoreViewModel.highestScore.observe(this) { highest ->
            binding.tvHighestScore.text = "最高得分: ${highest ?: 0}"
        }
        
        scoreViewModel.allScores.observe(this) { scores ->
            scoreAdapter.submitList(scores)
            binding.tvRecordCount.text = "共${scores.size}条记录"
        }
    }
}