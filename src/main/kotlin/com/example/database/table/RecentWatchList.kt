package com.example.database.table

import org.jetbrains.exposed.dao.id.UUIDTable

object RecentWatchList: UUIDTable("recentWatchList") {
    val userId = reference("userId", User)
    val watchListId=reference("watchListId",WatchList)
}