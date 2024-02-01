package com.example.exceptions

import io.ktor.http.*

class CommonException(val msg:String,val statusCode: HttpStatusCode):RuntimeException()
class InvalidRegisterDetails(val msg: String, val statusCode: HttpStatusCode) : RuntimeException()


