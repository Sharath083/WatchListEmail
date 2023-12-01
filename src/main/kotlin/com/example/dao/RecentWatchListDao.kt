package com.example.dao

import com.example.model.RecentWatchListResponse
import com.example.model.RecentWatchlistData
import com.example.model.Symbol
import java.util.UUID

interface RecentWatchListDao {
    suspend fun getRecentWatchList(uuid: String): RecentWatchListResponse
    suspend fun getAllWatchList(uuid: String): List<RecentWatchListResponse>
}