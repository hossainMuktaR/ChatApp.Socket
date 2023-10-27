package com.example.androidsocketclientapp.data.remote

import com.example.androidsocketclientapp.domain.model.Message
import com.example.androidsocketclientapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {
    suspend fun initSession(
        userName: String
    ) : Resource<Unit>
    suspend fun sendMessage(message: String)
    fun observeMessages(): Flow<Message>
    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://192.168.0.101:8080"
    }

    sealed class Endpoints(val url: String) {
        object ChatSocket: Endpoints("$BASE_URL/chat-socket")
    }
}