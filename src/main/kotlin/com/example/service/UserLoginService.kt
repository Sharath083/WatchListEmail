package com.example.service

import com.example.exceptions.CommonException
import com.example.exceptions.InvalidRegisterDetails
import com.example.model.LoginData
import com.example.model.UserResponse
import com.example.repository.UserDaoImpl
import io.ktor.http.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class UserLoginService: KoinComponent {
    private val userDaoImpl by inject<UserDaoImpl>()
    suspend fun userLoginService(details: LoginData): UserResponse {
        try {
            val uuid = userDaoImpl.userLoginCheck(details)
            return when (uuid.isNotEmpty()) {
                true -> {
                    UserResponse(uuid, HttpStatusCode.Created.toString())
                }
                else ->
                    throw CommonException(
                        "Db Error",
                        HttpStatusCode.BadRequest
                    )
            }
        } catch (e: ExposedSQLException) {
            throw CommonException(
                "DATABASE_EXPOSED_SQL_ERROR",
                HttpStatusCode.InternalServerError
            )
        }
    }

}