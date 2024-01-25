package com.example.route

import com.example.model.UserRegistration
import com.example.utils.appconstants.ApiEndPoints.USER_REGISTRATION
import com.example.utils.appconstants.ApiEndPoints.USER_ROUTE
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals


class UserRouteTesting{
    private val userRegisterDetails=UserRegistration("a","a@gmail.com","1234")
    @Test
    fun userRegistrationRouteTest()= testApplication {
        val serializedData = Json.encodeToString(userRegisterDetails)
        val response = client.post(USER_ROUTE + USER_REGISTRATION){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }




}