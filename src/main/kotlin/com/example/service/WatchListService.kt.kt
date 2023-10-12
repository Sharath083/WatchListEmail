package com.example.service

import com.example.model.DeleteWatchlist
import com.example.model.SuccessResponse
import com.example.model.UpdateWatchList
import com.example.model.WatchlistData
import com.example.repository.WatchListDaoImpl
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WatchListService:KoinComponent {
    private val watchListService by inject<WatchListDaoImpl>()
    suspend fun createWatchListService(watchlistData: WatchlistData):SuccessResponse{
        watchListService.createWatchList(watchlistData)
        return SuccessResponse("WatchList Created",HttpStatusCode.Created.toString())

    }
    suspend fun updateWatchListService(updateWatchList: UpdateWatchList):SuccessResponse{
        watchListService.updateWatchList(updateWatchList)
        return SuccessResponse("WatchList Updated",HttpStatusCode.Created.toString())
    }
    suspend fun deleteWatchList(deleteWatchlist: DeleteWatchlist):SuccessResponse{
        watchListService.deleteWatchList(deleteWatchlist)
        return SuccessResponse("WatchList Deleted",HttpStatusCode.Created.toString())

    }


}