package com.example.androidsocketclientapp.di

import com.example.androidsocketclientapp.data.remote.ChatSocketService
import com.example.androidsocketclientapp.data.remote.ChatSocketServiceImpl
import com.example.androidsocketclientapp.data.remote.MessageService
import com.example.androidsocketclientapp.data.remote.MessageServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
    @Provides
    @Singleton
    fun provideMessageService(client: HttpClient) : MessageService {
        return MessageServiceImpl(client)
    }
    @Provides
    @Singleton
    fun provideChatSocketService(client: HttpClient) : ChatSocketService {
        return ChatSocketServiceImpl(client)
    }
}