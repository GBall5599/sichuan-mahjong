package com.sichuanmahjong.game.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sichuanmahjong.game.model.MahjongTile
import com.sichuanmahjong.game.model.Player

/**
 * 游戏逻辑ViewModel
 */
class GameViewModel : ViewModel() {
    
    private val _currentPlayer = MutableLiveData<Player>()
    val currentPlayer: LiveData<Player> = _currentPlayer
    
    private val _gameState = MutableLiveData<String>()
    val gameState: LiveData<String> = _gameState
    
    private val _lastDiscardedTile = MutableLiveData<MahjongTile?>()
    val lastDiscardedTile: LiveData<MahjongTile?> = _lastDiscardedTile
    
    private var players = mutableListOf<Player>()
    private var currentPlayerIndex = 0
    private var tilePool = mutableListOf<MahjongTile>()
    private var gameInProgress = false
    
    init {
        initializePlayers()
    }
    
    /**
     * 初始化玩家
     */
    private fun initializePlayers() {
        players.clear()
        players.add(Player(0, "玩家", true))
        players.add(Player(1, "电脑1", false))
        players.add(Player(2, "电脑2", false))
        players.add(Player(3, "电脑3", false))
        
        _currentPlayer.value = players[0]
    }
    
    /**
     * 开始新游戏
     */
    fun startNewGame() {
        // 重置玩家状态
        players.forEach { player ->
            player.handTiles.clear()
            player.discardedTiles.clear()
            player.meldedTiles.clear()
        }
        
        // 创建牌池并洗牌
        tilePool = MahjongTile.createSichuanMahjongSet().toMutableList()
        tilePool.shuffle()
        
        // 发牌
        dealInitialTiles()
        
        currentPlayerIndex = 0
        gameInProgress = true
        _gameState.value = "游戏开始"
        _currentPlayer.value = players[0]
    }
    
    /**
     * 发初始手牌
     */
    private fun dealInitialTiles() {
        // 每人发13张牌
        repeat(13) {
            players.forEach { player ->
                if (tilePool.isNotEmpty()) {
                    player.drawTile(tilePool.removeAt(0))
                }
            }
        }
        
        // 庄家多摸一张
        if (tilePool.isNotEmpty()) {
            players[0].drawTile(tilePool.removeAt(0))
        }
    }
    
    /**
     * 玩家是否可以胡牌
     */
    fun canPlayerHu(): Boolean {
        return gameInProgress && players[0].canHu()
    }
    
    /**
     * 玩家是否可以碰牌
     */
    fun canPlayerPeng(): Boolean {
        val lastTile = _lastDiscardedTile.value ?: return false
        val player = players[0]
        val sameTypeCount = player.handTiles.count { 
            it.type == lastTile.type && it.value == lastTile.value 
        }
        return gameInProgress && sameTypeCount >= 2
    }
    
    /**
     * 玩家是否可以杠牌
     */
    fun canPlayerGang(): Boolean {
        val lastTile = _lastDiscardedTile.value ?: return false
        val player = players[0]
        val sameTypeCount = player.handTiles.count { 
            it.type == lastTile.type && it.value == lastTile.value 
        }
        return gameInProgress && sameTypeCount >= 3
    }
    
    /**
     * 玩家胡牌
     */
    fun playerHu() {
        if (canPlayerHu()) {
            players[0].score += 10 // 胡牌得分
            gameInProgress = false
            _gameState.value = "玩家胡牌！"
        }
    }
    
    /**
     * 玩家碰牌
     */
    fun playerPeng() {
        val lastTile = _lastDiscardedTile.value
        if (lastTile != null && canPlayerPeng()) {
            players[0].peng(lastTile)
            _gameState.value = "玩家碰牌"
            _currentPlayer.value = players[0]
        }
    }
    
    /**
     * 玩家杠牌
     */
    fun playerGang() {
        val lastTile = _lastDiscardedTile.value
        if (lastTile != null && canPlayerGang()) {
            players[0].gang(lastTile)
            players[0].score += 2 // 杠牌得分
            _gameState.value = "玩家杠牌"
            _currentPlayer.value = players[0]
        }
    }
    
    /**
     * 玩家过牌
     */
    fun playerPass() {
        nextPlayer()
    }
    
    /**
     * 下一个玩家
     */
    private fun nextPlayer() {
        if (!gameInProgress) return
        
        currentPlayerIndex = (currentPlayerIndex + 1) % 4
        val currentPlayer = players[currentPlayerIndex]
        
        // 电脑玩家自动出牌
        if (!currentPlayer.isHuman && tilePool.isNotEmpty()) {
            currentPlayer.drawTile(tilePool.removeAt(0))
            
            // 简单的AI逻辑：随机出牌
            if (currentPlayer.handTiles.isNotEmpty()) {
                val tileToDiscard = currentPlayer.handTiles.random()
                currentPlayer.discardTile(tileToDiscard)
                _lastDiscardedTile.value = tileToDiscard
                _gameState.value = "${currentPlayer.name}出牌: ${tileToDiscard.getDisplayName()}"
            }
            
            // 检查电脑是否胡牌
            if (currentPlayer.canHu()) {
                currentPlayer.score += 10
                gameInProgress = false
                _gameState.value = "${currentPlayer.name}胡牌！"
                return
            }
        }
        
        _currentPlayer.value = currentPlayer
    }
}