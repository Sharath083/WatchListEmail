package com.example.dao

import com.example.model.LoginData
import com.example.model.UserRegistration
import com.example.model.UserResponse
import java.util.UUID

interface UserDao {
    suspend fun userRegistration(details: UserRegistration): UUID
    suspend fun checkUser(name: String, email: String): Boolean
    suspend fun userLoginCheck(input: LoginData): String

}