package com.example.exceptions

import io.ktor.http.*

class InvalidNameException(val statusCode: HttpStatusCode):RuntimeException()
class InvalidPasswordException(val statusCode: HttpStatusCode):RuntimeException()
class InvalidEmailException(val statusCode: HttpStatusCode):RuntimeException()
class InvalidEmailFormatException(val statusCode: HttpStatusCode):RuntimeException()


class CommonException(val msg:String,val statusCode: HttpStatusCode):RuntimeException()
class InvalidRegisterDetails(val msg: String, val statusCode: HttpStatusCode) : RuntimeException()


