package com.example.repository

import com.example.dao.WatchListDao
import com.example.database.DatabaseFactory
import com.example.database.table.RecentWatchList
import com.example.database.table.WatchList
import com.example.entity.WatchListEntity
import com.example.exceptions.CommonException
import com.example.model.DeleteWatchlist
import com.example.model.UpdateWatchList
import com.example.model.WatchlistData
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.Instant
import java.util.*

class WatchListDaoImpl : WatchListDao {
    override suspend fun createWatchList(watchlistData: WatchlistData): UUID {
        return try {
            val symbol = Json.encodeToString(watchlistData.symbols)
            val userUid = UUID.fromString(watchlistData.userId)
            DatabaseFactory.dbQuery {
                val data = WatchList.insertAndGetId {
                    it[userId] = userUid
                    it[watchListName] = watchlistData.watchListName
                    it[createdAt] = Instant.now().toString()
                    it[updatedAt] = Instant.now().toString()
                    it[is_delete] = false
                    it[symbols] = symbol
                }
                RecentWatchList.insert {
                    it[userId] = userUid
                    it[watchListId] = data.value
                }
                data.value
            }
        } catch (e: Exception) {
            print(e)
            throw CommonException("Unable to Create WatchList", HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun updateWatchList(updateWatchList: UpdateWatchList ):Boolean {
        val userUid=UUID.fromString(updateWatchList.userId)
        val watchListUid=UUID.fromString(updateWatchList.watchListId)
        val symbol = Json.encodeToString(updateWatchList.symbols)
        return try {
            DatabaseFactory.dbQuery {
                WatchList.update({
                    WatchList.userId eq userUid and (WatchList.id eq watchListUid)
                }) {
                    it[updatedAt] = Instant.now().toString()
                    it[symbols] = symbol
                }>0
            }
        }catch (e:Exception){
            throw CommonException("Unable to delete WatchList", HttpStatusCode.InternalServerError)
        }
    }


    override suspend fun deleteWatchList(deleteWatchlist: DeleteWatchlist): Boolean {

        val userUid=UUID.fromString(deleteWatchlist.userId)
        val watchListUid=UUID.fromString(deleteWatchlist.watchlistId)
        return try {
            DatabaseFactory.dbQuery {
                RecentWatchList.deleteWhere { userId eq userUid and (watchListId eq watchListUid) }

                WatchList.update({
                    WatchList.userId eq userUid and (WatchList.id eq watchListUid)
                }) {
                    it[is_delete] = true
                }
            }>0
        }catch (e:Exception){
            throw CommonException("Unable to delete WatchList", HttpStatusCode.InternalServerError)
        }
    }


}