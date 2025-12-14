package com.sichuanmahjong.game.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sichuanmahjong.game.database.ScoreEntity
import com.sichuanmahjong.game.databinding.ItemScoreBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * 积分记录适配器
 */
class ScoreAdapter(
    private val onItemClick: (ScoreEntity) -> Unit
) : ListAdapter<ScoreEntity, ScoreAdapter.ScoreViewHolder>(ScoreDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val binding = ItemScoreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScoreViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class ScoreViewHolder(
        private val binding: ItemScoreBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(scoreEntity: ScoreEntity) {
            val dateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
            
            binding.apply {
                tvDate.text = dateFormat.format(Date(scoreEntity.gameDate))
                tvScore.text = "${if (scoreEntity.playerScore >= 0) "+" else ""}${scoreEntity.playerScore}"
                tvResult.text = if (scoreEntity.isWin) "胜" else "负"
                
                // 设置颜色
                tvScore.setTextColor(
                    if (scoreEntity.playerScore >= 0) {
                        binding.root.context.getColor(android.R.color.holo_green_dark)
                    } else {
                        binding.root.context.getColor(android.R.color.holo_red_dark)
                    }
                )
                
                tvResult.setTextColor(
                    if (scoreEntity.isWin) {
                        binding.root.context.getColor(android.R.color.holo_green_dark)
                    } else {
                        binding.root.context.getColor(android.R.color.holo_red_dark)
                    }
                )
                
                // 显示游戏时长
                if (scoreEntity.gameDuration > 0) {
                    val minutes = scoreEntity.gameDuration / 60
                    val seconds = scoreEntity.gameDuration % 60
                    tvDuration.text = "${minutes}分${seconds}秒"
                } else {
                    tvDuration.text = ""
                }
                
                root.setOnClickListener {
                    onItemClick(scoreEntity)
                }
            }
        }
    }
    
    class ScoreDiffCallback : DiffUtil.ItemCallback<ScoreEntity>() {
        override fun areItemsTheSame(oldItem: ScoreEntity, newItem: ScoreEntity): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: ScoreEntity, newItem: ScoreEntity): Boolean {
            return oldItem == newItem
        }
    }
}