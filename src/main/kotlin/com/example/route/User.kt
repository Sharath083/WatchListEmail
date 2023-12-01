package com.example.route

import com.example.exceptions.CommonException
import com.example.model.LoginData
import com.example.model.UserRegistration
import com.example.plugins.UserSession
import com.example.redis.getRequestHeader
import com.example.redis.getSessionParameters
import com.example.redis.setResponseHeader
import com.example.service.UserLoginService
import com.example.service.UserServices
import com.example.utils.appconstants.ApiEndPoints.USER_LOGIN
import com.example.utils.appconstants.ApiEndPoints.USER_REGISTRATION
import com.example.utils.appconstants.ApiEndPoints.USER_ROUTE
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable
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
            userLoginService.userLoginService(call.receive<LoginData>()).apply {
//                call.setResponseHeader("Header",this.uuid)
                call.sessions.set(UserSession(this.uuid))
                call.respond(HttpStatusCode.Created,this)
            }
        }
        get("/pro"){
            @Serializable
            data class Properties(
                var id:Int,
                var title:String,
                var description:String,
                var price:Int,
                var discountPercentage:Double,
                var rating:Double,
                var stock:Int,
                var brand:String,
                var category:String,
                var thumbnail:String,
                var images: List<String>
            )
            @Serializable
            data class Products(var products: List<Properties>)
             suspend fun propertiesList(url: String): Products? {
                    val httpClient = HttpClient(CIO){
                        install(ContentNegotiation) {
                            gson()
                        }
                    }
                    val response = httpClient.get(url)
                    return Gson().fromJson(response.bodyAsText(), Products::class.java)
                }
            call.respond(propertiesList("https://dummyjson.com/products")!!)

        }
//        authenticate ("AUTH_SESSION") {
            get {
//                val s=call.getSessionParameters()
                val s=call.sessions.get<UserSession>()?.accountId?:"{}"
                call.respond(s)
            }
//        }
    }



}