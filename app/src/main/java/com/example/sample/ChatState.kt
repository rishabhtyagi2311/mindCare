package com.example.sample
import android.graphics.Bitmap
import com.example.sample.data.Chat


data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    var prompt: String = "",
    val bitmap: Bitmap? = null
)