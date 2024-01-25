package com.example.database.table

import com.example.utils.helperfunctions.jsonb
import com.example.utils.helperfunctions.timeStamp
import org.jetbrains.exposed.dao.id.UUIDTable

object WatchList: UUIDTable("watchList"){
    val userId = uuid("userId").nullable()
    val watchListName=varchar("watchListName",25).nullable()
    var is_delete=bool("is_delete").default(false).nullable()
    val createdAt = timeStamp("createdAt").nullable()
    val updatedAt = timeStamp("updatedAt").nullable()
    val symbols = jsonb("symbols").nullable()
}