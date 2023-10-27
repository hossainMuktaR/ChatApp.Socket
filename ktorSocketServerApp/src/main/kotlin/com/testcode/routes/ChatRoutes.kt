package com.testcode.routes

import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import com.testcode.room.MemberAlreadyExistsException
import com.testcode.room.RoomController
import com.testcode.session.ChatSession
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import io.ktor.websocket.*
import java.lang.Exception
import java.util.*

fun Route.chatSocket(roomController: RoomController) {
    webSocket("/chat-socket"){
        val session = call.sessions.get<ChatSession>()
        if(session == null) {
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY,"No Session"))
            return@webSocket
        }
        try {
            roomController.onJoin(
                userName = session.username,
                sessionId = session.sessionid,
                socket = this
            )
            for(frame in incoming) {
                frame as? Frame.Text ?: continue
                roomController.sendMessage(
                    senderUserName = session.username,
                    message = frame.readText()
                )
            }

        } catch (e: MemberAlreadyExistsException) {
            call.respond(HttpStatusCode.Conflict)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            roomController.tryDisconnect(session.username)
        }
    }
}

fun Route.getAllMessages(roomController: RoomController){
    get("/messages") {
        call.respond(
            HttpStatusCode.OK,
            roomController.getAllMessages()
        )
    }
}