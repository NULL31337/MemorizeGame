package com.github.NULL31337.database

import com.github.NULL31337.model.User


data class UserDTO(
    val login: String,
    val password: String,
    val user_id: Int,
    val money: Int
) {
    constructor(user: User): this(user.login, user.password, user.user_id, user.money)
}