package com.sichuanmahjong.game.model

/**
 * 玩家类
 */
data class Player(
    val id: Int,
    val name: String,
    val isHuman: Boolean = false,
    var handTiles: MutableList<MahjongTile> = mutableListOf(),
    var discardedTiles: MutableList<MahjongTile> = mutableListOf(),
    var meldedTiles: MutableList<List<MahjongTile>> = mutableListOf(), // 碰、杠的牌
    var score: Int = 0
) {
    
    /**
     * 摸牌
     */
    fun drawTile(tile: MahjongTile) {
        handTiles.add(tile)
        sortHandTiles()
    }
    
    /**
     * 出牌
     */
    fun discardTile(tile: MahjongTile): Boolean {
        return if (handTiles.remove(tile)) {
            discardedTiles.add(tile)
            true
        } else {
            false
        }
    }
    
    /**
     * 碰牌
     */
    fun peng(tile: MahjongTile): Boolean {
        val sameTypeCount = handTiles.count { it.type == tile.type && it.value == tile.value }
        if (sameTypeCount >= 2) {
            val tilesToRemove = handTiles.filter { it.type == tile.type && it.value == tile.value }.take(2)
            handTiles.removeAll(tilesToRemove)
            meldedTiles.add(listOf(tile, tilesToRemove[0], tilesToRemove[1]))
            return true
        }
        return false
    }
    
    /**
     * 杠牌
     */
    fun gang(tile: MahjongTile): Boolean {
        val sameTypeCount = handTiles.count { it.type == tile.type && it.value == tile.value }
        if (sameTypeCount >= 3) {
            val tilesToRemove = handTiles.filter { it.type == tile.type && it.value == tile.value }.take(3)
            handTiles.removeAll(tilesToRemove)
            meldedTiles.add(listOf(tile, tilesToRemove[0], tilesToRemove[1], tilesToRemove[2]))
            return true
        }
        return false
    }
    
    /**
     * 整理手牌
     */
    private fun sortHandTiles() {
        handTiles.sortWith(compareBy<MahjongTile> { it.type }.thenBy { it.value })
    }
    
    /**
     * 检查是否可以胡牌
     */
    fun canHu(): Boolean {
        // 简化的胡牌判断逻辑
        val tileCount = mutableMapOf<String, Int>()
        handTiles.forEach { tile ->
            tileCount[tile.id] = tileCount.getOrDefault(tile.id, 0) + 1
        }
        
        return checkHuPattern(tileCount)
    }
    
    /**
     * 检查胡牌牌型
     */
    private fun checkHuPattern(tileCount: Map<String, Int>): Boolean {
        // 简化版胡牌判断：需要有对子和顺子/刻子组合
        var pairCount = 0
        var tripletCount = 0
        
        tileCount.values.forEach { count ->
            when (count) {
                2 -> pairCount++
                3 -> tripletCount++
                4 -> {
                    pairCount++
                    tripletCount++
                }
            }
        }
        
        // 基本胡牌条件：一个对子 + 若干刻子/顺子
        return pairCount >= 1 && (handTiles.size + meldedTiles.sumOf { it.size }) == 14
    }
}