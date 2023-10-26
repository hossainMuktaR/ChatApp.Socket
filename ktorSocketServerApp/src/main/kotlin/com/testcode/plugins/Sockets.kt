package com.testcode.plugins

import com.testcode.Connection
import io.ktor.network.sockets.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.lang.Exception
import java.time.Duration
import java.util.*
import kotlin.collections.LinkedHashSet

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        val userConnectionList = Collections.synchronizedSet<Connection>(LinkedHashSet())
        webSocket("/chat") {
            println("Adding user!")
            val thisConnection = Connection(this)
            userConnectionList += thisConnection
            try {
                send("You are Connected! Your UserName is ${thisConnection.name}")
                 for (frame in incoming) {
                     frame as? Frame.Text ?: continue
                     val receivedText = frame.readText()
                     val textWithUserName = "[${thisConnection.name}]: $receivedText"
                     userConnectionList.forEach {
                         it.session.send(textWithUserName)
                     }
                 }

            } catch (e: Exception) {
                println(e.localizedMessage)
            }finally {
                println("Removing $thisConnection")
                userConnectionList -= thisConnection
            }
        }
    }
}
