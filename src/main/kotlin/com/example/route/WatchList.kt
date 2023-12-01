package com.example.route

import com.example.model.*
import com.example.plugins.UserSession
import com.example.service.WatchListService
import com.example.utils.appconstants.ApiEndPoints.CREATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.DELETE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.UPDATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.WATCHLIST_ROUTE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.watchListRouting(){
    val watchListService by inject<WatchListService>()

    route(WATCHLIST_ROUTE){
        authenticate ("AUTH_SESSION") {
            post(CREATE_WATCHLIST) {

//                val details = call.receive<WatchlistData>()
                watchListService.createWatchListService(call.receive<WatchlistData>(), call.principal<UserSession>()!!.accountId).apply {
                    call.respond(HttpStatusCode.Created, this)
                }
            }

            put(UPDATE_WATCHLIST) {
//                val details = call.receive<UpdateWatchList>()
                watchListService.updateWatchListService(call.receive<UpdateWatchList>(),call.principal<UserSession>()!!.accountId).apply {
                    call.respond(HttpStatusCode.Accepted, this)
                }
            }
            delete(DELETE_WATCHLIST) {
//                val details = call.receive<DeleteWatchlist>()
                watchListService.deleteWatchList(call.receive<DeleteWatchlist>(),call.principal<UserSession>()!!.accountId).apply {
                    call.respond(HttpStatusCode.OK, this)
                }
            }
        }

    }
}
