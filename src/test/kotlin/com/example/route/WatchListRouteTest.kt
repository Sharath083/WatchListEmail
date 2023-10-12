package com.example.route

import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import com.example.di.myModule
import com.example.utils.TestInputs
import com.example.service.WatchListService
import com.example.utils.H2Database
import com.example.utils.appconstants.ApiEndPoints.CREATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.DELETE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.UPDATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.WATCHLIST_ROUTE
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

class WatchListRouteTest{
    @Test
    fun createWatchListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(TestInputs.watchlistData)
        val response = client.post(WATCHLIST_ROUTE + CREATE_WATCHLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }
    @Test
    fun updateWatchListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(TestInputs.updateWatchList)
        val response = client.put(WATCHLIST_ROUTE + UPDATE_WATCHLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.Accepted, response.status)
    }
    @Test
    fun deleteWatchListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(TestInputs.deleteWatchlist)
        val response = client.put(WATCHLIST_ROUTE + DELETE_WATCHLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

}
