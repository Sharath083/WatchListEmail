package com.example.service

import com.example.model.RecentWatchListResponse
import com.example.model.RecentWatchlistData
import com.example.repository.RecentWatchListDaoImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecentWatchListService:KoinComponent {
    private val recentWatchListDaoImpl by inject<RecentWatchListDaoImpl>()
    suspend fun getRecentWatchListService(recentWatchlistData: RecentWatchlistData):RecentWatchListResponse{
        recentWatchListDaoImpl.getRecentWatchList(recentWatchlistData).apply {
            return this
        }
    }
    suspend fun getAllWatchListService(recentWatchlistData: RecentWatchlistData):List<RecentWatchListResponse>{
        recentWatchListDaoImpl.getAllWatchList(recentWatchlistData).apply {
            return this
        }
    }
}