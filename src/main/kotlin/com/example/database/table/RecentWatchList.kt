package com.example.database.table

import org.jetbrains.exposed.dao.id.UUIDTable

object RecentWatchList: UUIDTable("recentWatchList") {
    val userId = uuid("userId").references(User.id)
    val watchListId=uuid("watchListId").references(WatchList.id)
}