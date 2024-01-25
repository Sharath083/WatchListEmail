package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(val name:String,val password:String)
