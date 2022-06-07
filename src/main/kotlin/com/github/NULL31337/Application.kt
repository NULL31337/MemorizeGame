package com.github.NULL31337

import com.github.NULL31337.database.toSHA256
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.github.NULL31337.plugins.*
import com.github.NULL31337.route.configureLoginRouting
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

fun main() {
    println("WHrV(oT\\W~PI_.:!~I*-c98<-?.PMrHH__sgDkwqvc+Siq>B_h".toSHA256())
    val config = HikariConfig()
    config.driverClassName = System.getenv("JDBC_DRIVER")
    config.jdbcUrl = System.getenv("JDBC_URL")
    config.validate()
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
