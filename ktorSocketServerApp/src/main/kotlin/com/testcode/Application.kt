package com.testcode

import com.testcode.di.mainModule
import com.testcode.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.ktor.plugin.koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(mainModule)
    }
    configureSockets()
    configureRouting()
    configureSerialization()
    configureSecurity()
    configureMonitoring()
}
