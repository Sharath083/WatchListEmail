package com.example.repository

import com.example.dao.UserDao
import com.example.database.DatabaseFactory
import com.example.database.table.User
import com.example.database.table.User.gmail
import com.example.database.table.User.password
import com.example.database.table.User.userName
import com.example.entity.UserEntity
import com.example.exceptions.CommonException
import com.example.exceptions.InvalidRegisterDetails
import com.example.model.LoginData
import com.example.model.UserRegistration
import io.ktor.http.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or
import java.util.UUID

class UserDaoImpl : UserDao {
    override suspend fun userRegistration(details: UserRegistration): UUID {
        try {
            return DatabaseFactory.dbQuery {
                val registered = UserEntity.new {
                    userName = details.name!!
                    gmail = details.email!!
                    password = details.password!!
                }
                registered.id.value
            }
        }catch (e:ExposedSQLException){
            throw CommonException("Unable to Create User", HttpStatusCode.BadRequest)
        }
    }
    suspend fun getUserGmail(uuid:String):String{
        try {
            return DatabaseFactory.dbQuery {
                UserEntity.find { User.id eq (UUID.fromString(uuid))
                }.single().gmail
            }
        }catch (e:ExposedSQLException){
            throw CommonException("Unable to Get Gmail", HttpStatusCode.BadRequest)
        }
    }

    override suspend fun checkUser(name: String, email: String): Boolean {
        try {
            return DatabaseFactory.dbQuery {
                UserEntity.find { (  userName eq name or (gmail eq email)) }.toList().isEmpty()
            }
        }catch (e:ExposedSQLException){
            throw CommonException("User already exists", HttpStatusCode.BadRequest)
        }
    }
    override suspend fun userLoginCheck(input: LoginData): String {
        try {
            return DatabaseFactory.dbQuery {
                UserEntity.find { (userName eq input.name and (password eq input.password)) }
                    .map{it.id}.firstOrNull().toString()

            }
        }catch (e:ExposedSQLException){
            throw CommonException("Invalid Login Details", HttpStatusCode.BadRequest)
        }
    }
}