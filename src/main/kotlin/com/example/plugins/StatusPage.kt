package com.example.plugins

import com.example.exceptions.*
import com.example.model.ExceptionResponse
import com.example.utils.appconstants.InfoMessage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        var statusCode: HttpStatusCode
        var message:String
        var status:String
        exception<Exception>{ call, cause ->
            when(cause){
                is RequestValidationException->{
                    statusCode= HttpStatusCode.BadRequest
                    message=cause.reasons.joinToString()
                    status="400 Bad Request"
                }
                is InvalidRegisterDetails->{
                    statusCode=cause.statusCode
                    message= cause.msg
                    status=cause.statusCode.toString()
                }
                is CommonException->{
                    statusCode=cause.statusCode
                    message= cause.msg
                    status=cause.statusCode.toString()
                }
                else->{
                    statusCode=HttpStatusCode.InternalServerError
                    message= "${InfoMessage.UNKNOWN_ERROR} $cause"
                    status=statusCode.toString()
                }
            }
            call.respond(statusCode, ExceptionResponse(
                response = message,
                msgId = statusCode.value,
                status = status
            ))
        }
    }
}