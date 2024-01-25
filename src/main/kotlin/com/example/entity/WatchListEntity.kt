package com.example.entity

import com.example.database.table.WatchList
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class WatchListEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<WatchListEntity>(WatchList)
    var userId by WatchList.userId
    var watchListName by WatchList.watchListName
    var createdAt by WatchList.createdAt
    var updatedAt by WatchList.updatedAt
    var isDelete by WatchList.is_delete
    var symbols by WatchList.symbols
}
