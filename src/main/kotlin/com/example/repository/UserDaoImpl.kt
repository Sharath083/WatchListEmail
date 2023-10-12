package com.example.repository

import com.example.dao.UserDao
import com.example.database.DatabaseFactory
import com.example.database.table.User
import com.example.database.table.User.gmail
import com.example.database.table.User.userName
import com.example.entity.UserEntity
import com.example.model.UserRegistration
import com.example.model.UserResponse
import org.jetbrains.exposed.sql.or
import java.util.UUID

class UserDaoImpl : UserDao {
    override suspend fun userRegistration(details: UserRegistration): UUID {
        return DatabaseFactory.dbQuery {
            val registered=UserEntity.new {
                userName = details.name!!
                gmail = details.email!!
                password = details.password!!
            }
            registered.id.value
        }
    }
    override suspend fun checkUser(name: String, email: String): Boolean {
        return DatabaseFactory.dbQuery {
            UserEntity.find { (userName eq name or (gmail eq email)) }
                .toList().isEmpty()
        }
    }
}