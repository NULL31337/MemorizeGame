package com.github.NULL31337.route

import com.github.NULL31337.database.UserDTO
import com.github.NULL31337.database.Users
import com.github.NULL31337.model.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    routing {
        post("/login")  {
            val receive = call.receive<User>()
            Users.insert(UserDTO(receive))
            call.respondText("${receive.login} ${receive.password}", status = HttpStatusCode.OK)
        }
    }
}