package com.example.dao

import com.example.model.DeleteWatchlist
import com.example.model.UpdateWatchList
import com.example.model.WatchlistData
import java.util.*

interface WatchListDao {
    suspend fun createWatchList(watchlistData: WatchlistData): UUID
    suspend fun updateWatchList(updateWatchList: UpdateWatchList):Boolean
    suspend fun deleteWatchList(deleteWatchlist: DeleteWatchlist):Boolean
}