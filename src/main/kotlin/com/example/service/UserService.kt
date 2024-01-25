package com.example.service

import com.example.exceptions.InvalidRegisterDetails
import com.example.model.UserRegistration
import com.example.model.UserResponse
import com.example.redis.RedisPool
import com.example.repository.UserDaoImpl
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserServices : KoinComponent {
    private val userDaoImpl by inject<UserDaoImpl>()
    suspend fun userRegistrationService(details: UserRegistration): UserResponse {
        return when (userDaoImpl.checkUser(details.name!!, details.email!!)) {
            true -> {
                val uuid = userDaoImpl.userRegistration(details)
                UserResponse(uuid.toString(), HttpStatusCode.Created.toString())
            }
            else ->
                throw InvalidRegisterDetails(
                    "${details.name} or ${details.email} Already Exists ",
                    HttpStatusCode.BadRequest
                )
        }
    }


}
