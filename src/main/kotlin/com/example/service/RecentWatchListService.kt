package com.example.service

import com.example.exceptions.CommonException
import com.example.model.RecentWatchListResponse
import com.example.repository.RecentWatchListDaoImpl
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecentWatchListService:KoinComponent {
    private val recentWatchListDaoImpl by inject<RecentWatchListDaoImpl>()
    suspend fun getRecentWatchListService(uuid: String):RecentWatchListResponse{
        try {
            recentWatchListDaoImpl.getRecentWatchList(uuid).apply {
                return this
            }
        }catch (e:Exception){
            throw CommonException(
                msg = "InfoMessage.RECENT_WATCHLIST_ILLEGAL_ARGUMENT_ERROR",
                statusCode = HttpStatusCode.InternalServerError
            )
        }

    }
    suspend fun getAllWatchListService(uuid: String):List<RecentWatchListResponse>{
        try {
            recentWatchListDaoImpl.getAllWatchList(uuid).apply {
                return this
            }
        }catch (e:NoSuchElementException){
            throw CommonException(
                msg = "Watch list is empty",
                statusCode = HttpStatusCode.InternalServerError
            )
        }

    }

}