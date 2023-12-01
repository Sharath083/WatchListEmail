package com.example.route

import com.example.utils.TestInputs
import com.example.utils.appconstants.ApiEndPoints.CREATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.DELETE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.UPDATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.WATCHLIST_ROUTE
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
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
