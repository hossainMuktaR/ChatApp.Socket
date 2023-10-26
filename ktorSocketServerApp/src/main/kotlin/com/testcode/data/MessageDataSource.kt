package com.testcode.data

import com.testcode.data.model.Message

interface MessageDataSource {
    suspend fun getAllMessages(): List<Message>
    suspend fun insertMessage(message: Message)
}