package com.example.route

import com.example.model.LoginData
import com.example.model.UserRegistration
import com.example.redis.RedisHelper
import com.example.redis.getSession
import com.example.redis.setResponseHeader
import com.example.service.UserLoginService
import com.example.service.UserServices
import com.example.utils.appconstants.ApiEndPoints.USER_LOGIN
import com.example.utils.appconstants.ApiEndPoints.USER_LOGOUT
import com.example.utils.appconstants.ApiEndPoints.USER_REGISTRATION
import com.example.utils.appconstants.ApiEndPoints.USER_ROUTE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.userRouting(){
    val userServices by inject<UserServices>()
    val userLoginService by inject<UserLoginService>()
    route(USER_ROUTE){
        post(USER_REGISTRATION) {
            val details=call.receive<UserRegistration>()
            userServices.userRegistrationService(details).apply {
                call.respond(HttpStatusCode.Created,this)
            }
        }
        post(USER_LOGIN) {
            val details=call.receive<LoginData>()
            userLoginService.userLoginService(details).apply {
                call.setResponseHeader("Session", "${details.name}|${this.uuid}")
                call.respond(HttpStatusCode.Created,"Logged in Successfully")
            }
        }
        post(USER_LOGOUT) {
            userLoginService.userLogoutService(call.getSession().first).apply {
                call.respond(HttpStatusCode.OK,this)
            }
        }
    }



}