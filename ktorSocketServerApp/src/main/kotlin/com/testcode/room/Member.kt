package com.testcode.room

import io.ktor.websocket.*

data class Member(
    val userName: String,
    val sessionId: String,
    val socket: WebSocketSession
)

class MemberAlreadyExistsException: Exception(
    "There is already a member with that username in the room."
)