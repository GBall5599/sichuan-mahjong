package com.sichuanmahjong.game

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sichuanmahjong.game.databinding.ActivityMainBinding
import com.sichuanmahjong.game.viewmodel.ScoreViewModel

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var scoreViewModel: ScoreViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        scoreViewModel = ViewModelProvider(this)[ScoreViewModel::class.java]
        
        setupUI()
        observeScore()
    }
    
    private fun setupUI() {
        binding.btnStartGame.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
        
        binding.btnScoreHistory.setOnClickListener {
            startActivity(Intent(this, ScoreActivity::class.java))
        }
        
        binding.btnSettings.setOnClickListener {
            // TODO: 实现设置界面
        }
        
        binding.btnExit.setOnClickListener {
            finish()
        }
    }
    
    private fun observeScore() {
        scoreViewModel.currentScore.observe(this) { score ->
            binding.tvCurrentScore.text = "当前积分: $score"
        }
        
        scoreViewModel.totalGames.observe(this) { games ->
            binding.tvTotalGames.text = "总局数: $games"
        }
        
        scoreViewModel.winRate.observe(this) { rate ->
            binding.tvWinRate.text = "胜率: ${String.format("%.1f", rate)}%"
        }
    }
}