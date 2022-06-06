package com.github.NULL31337.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val user_id: Int = 0,
    val login: String,
    val password: String,
    val money: Int = 42
)