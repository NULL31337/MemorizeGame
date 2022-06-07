package com.github.NULL31337.database

import com.auth0.jwt.algorithms.Algorithm
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.security.MessageDigest
fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

fun String.toSHA256(): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
    return bytes.toHex().uppercase()
}
object Users: Table("users") {
    private val login = Users.varchar("login", length = 42)
    private val password = Users.varchar("password", length = 64)
    private val user_id = Users.integer("user_id")
    private val money = Users.integer("money")

    fun insert(userDTO: UserDTO){
        transaction {
            Users.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password.toSHA256()
                it[user_id] = userDTO.user_id
                it[money] = userDTO.money
            }
        }
    }
    fun getByLogin(login: String): UserDTO? {
        return try {
            transaction {
                val user = Users.select { Users.login.eq(login) }.single()
                UserDTO(
                    user[Users.login],
                    user[password],
                    user[user_id],
                    user[money],
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}