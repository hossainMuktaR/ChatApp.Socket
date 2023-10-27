package com.example.androidsocketclientapp.presentation.chat

import com.example.androidsocketclientapp.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)