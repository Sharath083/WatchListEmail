package com.example.route

import com.example.model.*
import com.example.plugins.UserSession
import com.example.redis.getSession
import com.example.service.*
import com.example.utils.appconstants.ApiEndPoints.CREATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.DELETE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.UPDATE_WATCHLIST
import com.example.utils.appconstants.ApiEndPoints.WATCHLIST_GMAIL
import com.example.utils.appconstants.ApiEndPoints.WATCHLIST_PDF
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
    val pdfGenerationService by inject<PdfGenerationService>()
    val gmailGenerationService by inject<GmailGenerationService>()

    route(WATCHLIST_ROUTE){
            post(CREATE_WATCHLIST) {
//                val details = call.receive<WatchlistData>()
                watchListService.createWatchListService(
                    call.receive<WatchlistData>(),
                    call.getSession().second
                ).apply {
                    call.respond(HttpStatusCode.Created, this)
                }
            }

            put(UPDATE_WATCHLIST) {
//                val details = call.receive<UpdateWatchList>()
                watchListService.updateWatchListService(
                    call.receive<UpdateWatchList>(),
                    call.getSession().second
                ).apply {
                    call.respond(HttpStatusCode.Accepted, this)
                }
            }
            delete(DELETE_WATCHLIST) {
//                val details = call.receive<DeleteWatchlist>()
                watchListService.deleteWatchList(
                    call.receive<DeleteWatchlist>(),
                    call.getSession().second
                ).apply {
                    call.respond(HttpStatusCode.OK, this)
                }
            }


            get(WATCHLIST_PDF) {
                val pdfFile = pdfGenerationService
                    .generateEmptyPdf(call.getSession().second)
                call.response.header(
                    HttpHeaders.ContentDisposition,
                    "attachment; filename=watchListDetails.pdf")
                call.respondFile(pdfFile)
            }

            get(WATCHLIST_GMAIL) {
                gmailGenerationService.emailCheck(call.getSession().second).apply {
                    call.respond(HttpStatusCode.OK, this)
                }
            }
        }

}
