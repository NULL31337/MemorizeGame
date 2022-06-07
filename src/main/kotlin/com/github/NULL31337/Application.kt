package com.github.NULL31337

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.github.NULL31337.plugins.*
import com.github.NULL31337.route.configureLoginRouting
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

fun main() {
    val config = HikariConfig("hikari.properties")
    val dataSource = HikariDataSource(config)

    Database.connect(dataSource)
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureRouting()
//        configureSecurity()
        configureLoginRouting()
        configureTemplating()
        configureSerialization()
        configureSockets()
    }.start(wait = true)
}
