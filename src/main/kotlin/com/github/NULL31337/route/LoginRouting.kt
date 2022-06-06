package com.github.NULL31337.route

import com.github.NULL31337.model.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    routing {
        post("/login")  {
            val recive = call.receive<User>()
            call.respondText("${recive.login} ${recive.password}", status = HttpStatusCode.OK)
        }
    }
}