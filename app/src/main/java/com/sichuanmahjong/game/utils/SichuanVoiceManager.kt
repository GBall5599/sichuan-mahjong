package com.sichuanmahjong.game.utils

import android.content.Context
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * 四川话语音管理器
 */
class SichuanVoiceManager(private val context: Context) {
    
    private var mediaPlayer: MediaPlayer? = null
    private var textToSpeech: TextToSpeech? = null
    private var isTtsReady = false
    
    private val voiceMap = mapOf(
        "hu" to "sichuan_hu",
        "peng" to "sichuan_peng", 
        "gang" to "sichuan_gang",
        "chi" to "sichuan_chi",
        "start" to "sichuan_start",
        "win" to "sichuan_win",
        "lose" to "sichuan_lose"
    )
    
    init {
        initTextToSpeech()
    }
    
    /**
     * 播放四川话语音
     */
    fun playSichuanVoice(action: String) {
        try {
            val resourceName = voiceMap[action] ?: return
            val resourceId = context.resources.getIdentifier(
                resourceName, 
                "raw", 
                context.packageName
            )
            
            if (resourceId != 0) {
                stopCurrentVoice()
                mediaPlayer = MediaPlayer.create(context, resourceId)
                mediaPlayer?.setOnCompletionListener {
                    it.release()
                }
                mediaPlayer?.start()
            } else {
                Log.w("SichuanVoice", "Voice resource not found: $resourceName")
                // 如果没有语音文件，使用文字提示
                playTextToSpeech(action)
            }
        } catch (e: Exception) {
            Log.e("SichuanVoice", "Error playing voice: ${e.message}")
            playTextToSpeech(action)
        }
    }
    
    /**
     * 初始化文字转语音
     */
    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech?.setLanguage(Locale.CHINESE)
                isTtsReady = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED
                Log.d("SichuanVoice", "TTS initialized: $isTtsReady")
            }
        }
    }
    
    /**
     * 从文本文件读取语音内容
     */
    private fun readVoiceText(resourceName: String): String? {
        return try {
            val resourceId = context.resources.getIdentifier(resourceName, "raw", context.packageName)
            if (resourceId != 0) {
                val inputStream = context.resources.openRawResource(resourceId)
                val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
                val text = reader.readText()
                reader.close()
                text.trim()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("SichuanVoice", "Error reading voice text: ${e.message}")
            null
        }
    }
    
    /**
     * 使用系统TTS播放文字（备用方案）
     */
    private fun playTextToSpeech(action: String) {
        if (!isTtsReady) {
            Log.w("SichuanVoice", "TTS not ready")
            return
        }
        
        val resourceName = voiceMap[action]
        val text = if (resourceName != null) {
            readVoiceText(resourceName) ?: getDefaultText(action)
        } else {
            getDefaultText(action)
        }
        
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        Log.d("SichuanVoice", "TTS: $text")
    }
    
    /**
     * 获取默认文本
     */
    private fun getDefaultText(action: String): String {
        return when (action) {
            "hu" -> "胡了"
            "peng" -> "碰"
            "gang" -> "杠"
            "chi" -> "吃"
            "start" -> "开始"
            "win" -> "赢了"
            "lose" -> "输了"
            else -> action
        }
    }
    
    /**
     * 停止当前播放的语音
     */
    private fun stopCurrentVoice() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        mediaPlayer = null
    }
    
    /**
     * 释放资源
     */
    fun release() {
        stopCurrentVoice()
        textToSpeech?.let {
            it.stop()
            it.shutdown()
        }
        textToSpeech = null
    }
    
    /**
     * 设置音量
     */
    fun setVolume(volume: Float) {
        mediaPlayer?.setVolume(volume, volume)
    }
}