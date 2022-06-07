package com.github.NULL31337.route

import com.github.NULL31337.database.UserDTO
import com.github.NULL31337.database.Users
import com.github.NULL31337.database.toSHA256
import com.github.NULL31337.model.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    routing {
        post("/register")  {
            val receive = call.receive<User>()
            val user = Users.getByLogin(receive.login)
            if (user != null) {
                call.respondText("Login already exist", status = HttpStatusCode.Conflict)
            } else {
                Users.insert(UserDTO(receive))
                call.respondText("Create successfully", status = HttpStatusCode.OK)
            }
        }
        post("/login")  {
            val receive = call.receive<User>()
            val user = Users.getByLogin(receive.login)
            if (user == null) {
                call.respondText("Login doesn't exist", status = HttpStatusCode.Conflict)
            } else {
                if (receive.password.toSHA256() != user.password) {
                    call.respondText("Incorrect password", status = HttpStatusCode.Conflict)
                } else {
                    call.respondText("Login successfully", status = HttpStatusCode.OK)
                }
            }
        }
    }
}