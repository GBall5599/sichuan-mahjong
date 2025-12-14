package com.sichuanmahjong.game

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sichuanmahjong.game.databinding.ActivityGameBinding
import com.sichuanmahjong.game.model.MahjongTile
import com.sichuanmahjong.game.model.Player
import com.sichuanmahjong.game.utils.SichuanVoiceManager
import com.sichuanmahjong.game.viewmodel.GameViewModel

class GameActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityGameBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var voiceManager: SichuanVoiceManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        voiceManager = SichuanVoiceManager(this)
        
        setupUI()
        observeGame()
        startNewGame()
    }
    
    private fun setupUI() {
        binding.btnHu.setOnClickListener {
            if (gameViewModel.canPlayerHu()) {
                voiceManager.playSichuanVoice("hu")
                gameViewModel.playerHu()
                showGameResult("恭喜胡牌！")
            } else {
                Toast.makeText(this, "不能胡牌", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.btnPeng.setOnClickListener {
            if (gameViewModel.canPlayerPeng()) {
                voiceManager.playSichuanVoice("peng")
                gameViewModel.playerPeng()
            } else {
                Toast.makeText(this, "不能碰", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.btnGang.setOnClickListener {
            if (gameViewModel.canPlayerGang()) {
                voiceManager.playSichuanVoice("gang")
                gameViewModel.playerGang()
            } else {
                Toast.makeText(this, "不能杠", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.btnGuo.setOnClickListener {
            gameViewModel.playerPass()
        }
        
        binding.btnNewGame.setOnClickListener {
            startNewGame()
        }
        
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
    
    private fun observeGame() {
        gameViewModel.currentPlayer.observe(this) { player ->
            updatePlayerInfo(player)
        }
        
        gameViewModel.gameState.observe(this) { state ->
            updateGameState(state)
        }
        
        gameViewModel.lastDiscardedTile.observe(this) { tile ->
            tile?.let {
                binding.tvLastTile.text = "上家出牌: ${it.getDisplayName()}"
            }
        }
    }
    
    private fun updatePlayerInfo(player: Player) {
        binding.tvPlayerScore.text = "积分: ${player.score}"
        binding.tvHandTileCount.text = "手牌: ${player.handTiles.size}张"
        
        // 更新手牌显示（简化版）
        val handTilesText = player.handTiles.joinToString(" ") { it.getDisplayName() }
        binding.tvHandTiles.text = handTilesText
    }
    
    private fun updateGameState(state: String) {
        binding.tvGameState.text = state
    }
    
    private fun startNewGame() {
        gameViewModel.startNewGame()
        Toast.makeText(this, "新游戏开始", Toast.LENGTH_SHORT).show()
    }
    
    private fun showGameResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        // 可以添加游戏结束后的积分更新逻辑
    }
    
    override fun onDestroy() {
        super.onDestroy()
        voiceManager.release()
    }
}