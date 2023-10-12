package com.example.repository

import com.example.dao.RecentWatchListDao
import com.example.database.DatabaseFactory
import com.example.database.table.WatchList
import com.example.exceptions.CommonException
import com.example.model.RecentWatchListResponse
import com.example.model.RecentWatchlistData
import com.example.model.Symbol
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import java.util.*
import kotlin.collections.List

class RecentWatchListDaoImpl : RecentWatchListDao {
    override suspend fun getRecentWatchList(recentWatchlistData: RecentWatchlistData): RecentWatchListResponse {
        val userUid = UUID.fromString(recentWatchlistData.userId)
        return try {
            DatabaseFactory.dbQuery {
                val data=WatchList.slice(WatchList.symbols,WatchList.watchListName)
                    .select(WatchList.userId eq userUid and (WatchList.is_delete eq false))
                    .orderBy(WatchList.createdAt)
                    .map { it[WatchList.watchListName] to it[WatchList.symbols] }
                    .last()
                RecentWatchListResponse(data.first,Json.decodeFromString(data.second))
            }
        }catch (e:Exception){
            throw CommonException("Unable to Fetch Recent WatchList", HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun getAllWatchList(recentWatchlistData: RecentWatchlistData): List<RecentWatchListResponse> {
        val userUid = UUID.fromString(recentWatchlistData.userId)
        return try {
            DatabaseFactory.dbQuery {
                WatchList.slice(WatchList.symbols,WatchList.watchListName)
                    .select(WatchList.userId eq userUid and (WatchList.is_delete eq false))
                    .map {
                        RecentWatchListResponse(it[WatchList.watchListName] , Json.decodeFromString(it[WatchList.symbols]) )
                    }
            }
        }catch (e:Exception){
            throw CommonException("Unable to Fetch  WatchList", HttpStatusCode.InternalServerError)
        }
    }
}