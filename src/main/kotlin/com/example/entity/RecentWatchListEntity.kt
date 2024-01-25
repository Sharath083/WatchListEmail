package com.example.entity

import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class RecentWatchListEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<RecentWatchListEntity>(WatchList)

    var userId by  RecentWatchList.userId
    var watchListId by RecentWatchList.watchListId

}
