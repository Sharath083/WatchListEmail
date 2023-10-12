package com.example.route

import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import com.example.di.myModule
import com.example.model.UserRegistration
import com.example.utils.TestInputs
import com.example.service.UserServices
import com.example.utils.H2Database
import com.example.utils.appconstants.ApiEndPoints.USER_REGISTRATION
import com.example.utils.appconstants.ApiEndPoints.USER_ROUTE
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import java.sql.Connection
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