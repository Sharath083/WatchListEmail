package com.example.plugins

import com.example.route.recentWatchList
import com.example.route.userRouting
import com.example.route.watchListRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRouting()
        watchListRouting()
        recentWatchList()
    }
}
