package com.example.dao

import com.example.model.DeleteWatchlist
import com.example.model.UpdateWatchList
import com.example.model.WatchlistData
import java.util.*

interface WatchListDao {
    suspend fun createWatchList(watchlistData: WatchlistData,uuid:String): UUID
    suspend fun updateWatchList(updateWatchList: UpdateWatchList,uuid:String):Boolean
    suspend fun deleteWatchList(deleteWatchlist: DeleteWatchlist,uuid:String):Boolean
    suspend fun getWatchlistCountAndSameNameWatchlistCount(accountId: String, watchlistName: String) :Int

}