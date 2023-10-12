package com.example.route

import com.example.model.*
import com.example.repository.RecentWatchListDaoImpl
import com.example.service.UserServices
import com.example.service.WatchListService
import com.example.utils.appconstants.ApiEndPoints.CREATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.DELETE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.RECENT_WATCHLIST_ROUTE
import com.example.utils.appconstants.ApiEndPoints.UPDATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.WATCHLIST_ROUTE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.watchListRouting(){
    val watchListService by inject<WatchListService>()

    route(WATCHLIST_ROUTE){
        post(CREATE_WATCHLIST) {
            val details=call.receive<WatchlistData>()
            watchListService.createWatchListService(details).apply {
                call.respond(HttpStatusCode.Created,this)
            }
        }
        put(UPDATE_WATCHLIST) {
            val details=call.receive<UpdateWatchList>()
            watchListService.updateWatchListService(details).apply {
                call.respond(HttpStatusCode.Accepted,this)
            }
        }
        delete(DELETE_WATCHLIST) {
            val details=call.receive<DeleteWatchlist>()
            watchListService.deleteWatchList(details).apply {
                call.respond(HttpStatusCode.OK,this)
            }
        }

    }
}
