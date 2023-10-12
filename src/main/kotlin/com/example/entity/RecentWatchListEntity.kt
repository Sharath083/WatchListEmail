package com.example.entity

import com.example.database.table.User
import com.example.database.table.WatchList
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class RecentWatchListEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<RecentWatchListEntity>(WatchList)

    var userId by UserEntity referencedOn User.id
    var watchListId by WatchListEntity referencedOn WatchList.id

}
