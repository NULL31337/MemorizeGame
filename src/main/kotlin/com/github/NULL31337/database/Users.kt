package com.github.NULL31337.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table("users") {
    private val login = Users.varchar("login", length = 25)
    private val password = Users.varchar("password", length = 50)
    private val user_id = Users.integer("user_id")
    private val money = Users.integer("money")

    fun insert(userDTO: UserDTO){
        transaction {
            Users.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[user_id] = userDTO.user_id
                it[money] = userDTO.money
            }
        }
    }
}