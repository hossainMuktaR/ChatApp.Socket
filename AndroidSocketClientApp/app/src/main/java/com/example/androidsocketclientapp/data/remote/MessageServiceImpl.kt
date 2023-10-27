package com.example.androidsocketclientapp.data.remote

import android.util.Log
import com.example.androidsocketclientapp.data.remote.dto.MessageDto
import com.example.androidsocketclientapp.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class MessageServiceImpl(
    private val client: HttpClient
): MessageService {

    override suspend fun getAllMessages(): List<Message> {
        return try {
            val body = client.get { url(MessageService.Endpoints.GetAllMessages.url) }.body<List<MessageDto>>()
            Log.i("linebreak", body.toString())
            body.map { it.toMessage() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}