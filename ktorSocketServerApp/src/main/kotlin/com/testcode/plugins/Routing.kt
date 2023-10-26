package com.testcode.plugins

import com.testcode.room.RoomController
import com.testcode.routes.chatSocket
import com.testcode.routes.getAllMessages
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    routing {
        chatSocket(roomController)
        getAllMessages(roomController)
    }
}
