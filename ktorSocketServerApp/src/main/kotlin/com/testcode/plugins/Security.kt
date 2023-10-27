package com.testcode.plugins

import com.testcode.session.ChatSession
import io.ktor.server.application.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<ChatSession>("SESSION")
    }

    intercept(ApplicationCallPipeline.Plugins){
        if(call.sessions.get<ChatSession>() == null ) {
            val userName = call.parameters["userName"] ?: "Guest"
            call.sessions.set(ChatSession(userName, generateSessionId()))
        }
    }
}