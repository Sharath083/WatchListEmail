package com.example.route

import com.example.model.UserRegistration
import com.example.service.RecentWatchListService
import com.example.service.UserServices
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

    route(USER_ROUTE){
        post(USER_REGISTRATION) {
            val details=call.receive<UserRegistration>()
            userServices.userRegistrationService(details).apply {
                call.respond(HttpStatusCode.Created,this)
            }
        }
    }



}