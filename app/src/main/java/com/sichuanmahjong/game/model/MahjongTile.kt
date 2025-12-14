package com.sichuanmahjong.game.model

/**
 * 麻将牌类型
 */
enum class TileType {
    WAN,    // 万
    TIAO,   // 条
    TONG,   // 筒
    FENG,   // 风牌
    JIAN    // 箭牌
}

/**
 * 麻将牌数据类
 */
data class MahjongTile(
    val type: TileType,
    val value: Int,
    val id: String = "${type.name}_$value"
) {
    companion object {
        /**
         * 创建四川麻将牌组（108张）
         */
        fun createSichuanMahjongSet(): List<MahjongTile> {
            val tiles = mutableListOf<MahjongTile>()
            
            // 万、条、筒各1-9，每张4个
            for (type in listOf(TileType.WAN, TileType.TIAO, TileType.TONG)) {
                for (value in 1..9) {
                    repeat(4) {
                        tiles.add(MahjongTile(type, value))
                    }
                }
            }
            
            return tiles
        }
    }
    
    /**
     * 获取牌的显示名称
     */
    fun getDisplayName(): String {
        return when (type) {
            TileType.WAN -> "${value}万"
            TileType.TIAO -> "${value}条"
            TileType.TONG -> "${value}筒"
            TileType.FENG -> when (value) {
                1 -> "东"
                2 -> "南"
                3 -> "西"
                4 -> "北"
                else -> "风$value"
            }
            TileType.JIAN -> when (value) {
                1 -> "中"
                2 -> "发"
                3 -> "白"
                else -> "箭$value"
            }
        }
    }
}