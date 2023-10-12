package com.example.dao

import com.example.model.RecentWatchListResponse
import com.example.model.RecentWatchlistData
import com.example.model.Symbol

interface RecentWatchListDao {
    suspend fun getRecentWatchList(recentWatchlistData: RecentWatchlistData): RecentWatchListResponse
    suspend fun getAllWatchList(recentWatchlistData: RecentWatchlistData): List<RecentWatchListResponse>
}