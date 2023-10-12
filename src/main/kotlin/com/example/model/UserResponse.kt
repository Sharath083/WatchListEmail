package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(val uuid:String,val message:String)
