package com.example.utils

import com.example.database.table.WatchList
import com.example.database.table.WatchList.is_delete
import com.example.entity.UserEntity
import com.example.entity.WatchListEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.*

object MockObject {

    fun userEntityMock():String{
        return transaction {
            UserEntity.new {
                userName = "sharath"
                gmail = "sharath@gmail.com"
                password = "123456"
            }.id.value.toString()
            UserEntity.new {
                userName = "sharath1"
                gmail = "sharath1@gmail.com"
                password = "123456"
            }.id.value.toString()
            UserEntity.new {
                userName = "sharath2"
                gmail = "sharath2@gmail.com"
                password = "123456"
            }.id.value.toString()
            UserEntity.new {
                userName = "sharath3"
                gmail = "sharath3@gmail.com"
                password = "123456"
            }.id.value.toString()
        }
    }
    fun sampleUuid():UUID{
        return UserEntity.new {
                userName = "sharath4"
                gmail = "sharath4@gmail.com"
                password = "123456"
            }.id.value

    }
    fun watchListZEntityMock() {
        transaction {
            val uuid= sampleUuid()

            WatchListEntity.new {
                userId = uuid
                watchListName = "sample1"
                createdAt = Instant.now().toString()
                updatedAt = Instant.now().toString()
                isDelete = false
                symbols = Json.encodeToString(TestInputs.symbols)
            }.id.value.toString()
            WatchListEntity.new {
                userId = uuid
                watchListName = "sample2"
                createdAt = Instant.now().toString()
                updatedAt = Instant.now().toString()
                isDelete = false
                symbols = Json.encodeToString(TestInputs.symbols)
            }.id.value.toString()

        }
    }





}
