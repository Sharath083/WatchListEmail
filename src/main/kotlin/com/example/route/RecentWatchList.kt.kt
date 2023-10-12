package com.example.route

import com.example.model.RecentWatchlistData
import com.example.repository.RecentWatchListDaoImpl
import com.example.repository.UserDaoImpl
import com.example.service.RecentWatchListService
import com.example.utils.appconstants.ApiEndPoints
import com.example.utils.appconstants.ApiEndPoints.GET_ALL_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.GET_RECENT_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.RECENT_WATCHLIST_ROUTE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.component.inject
import org.koin.ktor.ext.inject

fun Route.recentWatchList(){
    val recentWatchListService by inject<RecentWatchListService>()
    route(RECENT_WATCHLIST_ROUTE){
        get(GET_RECENT_WATCHLIST){
            val details=call.receive<RecentWatchlistData>()
            recentWatchListService.getRecentWatchListService(details).apply {
                call.respond(HttpStatusCode.OK,this)
            }
        }
        get(GET_ALL_WATCHLIST){
            val details=call.receive<RecentWatchlistData>()
            recentWatchListService.getAllWatchListService(details).apply {
                call.respond(HttpStatusCode.OK,this)
            }
        }
    }
}