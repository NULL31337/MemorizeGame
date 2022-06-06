package com.github.NULL31337

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.github.NULL31337.plugins.*
import com.github.NULL31337.route.configureLoginRouting

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        configureRouting()
//        configureSecurity()
        configureLoginRouting()
        configureTemplating()
        configureSerialization()
        configureSockets()
    }.start(wait = true)
}
