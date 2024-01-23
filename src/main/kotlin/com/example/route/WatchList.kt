package com.example.route

import com.example.model.*
import com.example.plugins.UserSession
import com.example.service.*
import com.example.utils.appconstants.ApiEndPoints
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
            post(CREATE_WATCHLIST) {

//                val details = call.receive<WatchlistData>()
                watchListService.createWatchListService(
                    call.receive<WatchlistData>(),
                    call.request.headers["Session"]!!.split("|")[1]
                ).apply {
                    call.respond(HttpStatusCode.Created, this)
                }
            }

            put(UPDATE_WATCHLIST) {
//                val details = call.receive<UpdateWatchList>()
                watchListService.updateWatchListService(
                    call.receive<UpdateWatchList>(),
                    call.request.headers["Session"]!!.split("|")[1]
                ).apply {
                    call.respond(HttpStatusCode.Accepted, this)
                }
            }
            delete(DELETE_WATCHLIST) {
//                val details = call.receive<DeleteWatchlist>()
                watchListService.deleteWatchList(
                    call.receive<DeleteWatchlist>(),
                    call.request.headers["Session"]!!.split("|")[1]
                ).apply {
                    call.respond(HttpStatusCode.OK, this)
                }
            }


            get("/pdf") {
                val templatePath = "D:\\BlankPdf.pdf"
                val pdfFile = GeneratePdf()
                    .generateEmptyPdf(call.principal<UserSession>()?.accountId!!, templatePath)
                call.response.header(
                    HttpHeaders.ContentDisposition,
                    "attachment; filename=watchListDetails.pdf")
                call.respondFile(pdfFile)
            }

            get("/gmail") {
                val uuid=call.request.headers["Session"]!!.split("|")[1]
                GenerateGmail().generateEmailWithAttachment(uuid).apply {
                    call.respond(HttpStatusCode.OK, "GMAIL SENT SUCCESSFULLY")
                }
            }
        }

}
