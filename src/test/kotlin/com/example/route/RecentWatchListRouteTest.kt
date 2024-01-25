package com.example.route

import com.example.utils.TestInputs
import com.example.utils.appconstants.ApiEndPoints.GET_ALL_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.GET_RECENT_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.RECENT_WATCHLIST_ROUTE
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class RecentWatchListRouteTest{
    @Test
    fun getRecentWatchListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(TestInputs.recentWatchlistData)
        val response = client.get(RECENT_WATCHLIST_ROUTE + GET_RECENT_WATCHLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun getAllRecentWatchListRouteTest()= testApplication {
        val serializedData = Json.encodeToString(TestInputs.recentWatchlistData)
        val response = client.get(RECENT_WATCHLIST_ROUTE + GET_ALL_WATCHLIST){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedData)
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }
}