package com.example.database.table

import com.example.utils.helperfunctions.jsonb
import com.example.utils.helperfunctions.timeStamp
import org.jetbrains.exposed.dao.id.UUIDTable

object WatchList: UUIDTable("watchList"){
    val userId = uuid("userId").references(User.id)
    val watchListName=varchar("watchListName",10)
    var is_delete=bool("is_delete").default(false)
    val createdAt = timeStamp("createdAt")
    val updatedAt = timeStamp("updatedAt")
    val symbols = jsonb("symbols")
}