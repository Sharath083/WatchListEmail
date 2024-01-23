package com.example.repository

import com.example.dao.WatchListDao
import com.example.database.DatabaseFactory
import com.example.database.table.RecentWatchList
import com.example.database.table.WatchList
import com.example.database.table.WatchList.userId
import com.example.database.table.WatchList.watchListName
import com.example.entity.RecentWatchListEntity
import com.example.entity.WatchListEntity
import com.example.exceptions.CommonException
import com.example.model.DeleteWatchlist
import com.example.model.RecentWatchListResponse
import com.example.model.UpdateWatchList
import com.example.model.WatchlistData
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.util.*

class WatchListDaoImpl : WatchListDao {
    override suspend fun createWatchList(watchlistData: WatchlistData,uuid:String):UUID {
        return try {
            val symbol = Json.encodeToString(watchlistData.symbols)
            val userUid = UUID.fromString(uuid)
            DatabaseFactory.dbQuery {
                val data =WatchListEntity.new {
                    userId=userUid
                    watchListName=watchlistData.watchListName
                    createdAt=Instant.now().toString()
                    updatedAt=Instant.now().toString()
                    isDelete=false
                    symbols= symbol
                }.id.value
//                RecentWatchListEntity.new{
//                    userId = userUid
//                    watchListId = data
//                }
                data
            }
        } catch (e: ExposedSQLException) {
            throw CommonException("Unable to Create WatchList", HttpStatusCode.InternalServerError)
        }
    }
    override suspend fun getWatchlistCountAndSameNameWatchlistCount(accountId: String, watchlistName: String):Int {
        var sameNamedCount = 0
        transaction {
            try {
                sameNamedCount=WatchListEntity.find { ( userId eq UUID.fromString(accountId)  and (watchListName eq watchlistName)) }
                    .count().toInt()
            } catch (e: ExposedSQLException) {
                throw CommonException(
                    msg = "WATCHLIST_CREATE_COUNT_DATABASE_EXPOSED_ERROR",
                    statusCode = HttpStatusCode.InternalServerError
                )
            }
        }
        return sameNamedCount
    }

    override suspend fun updateWatchList(updateWatchList: UpdateWatchList,uuid: String ):Boolean {
        val userUid=UUID.fromString(uuid)
        val symbol = Json.encodeToString(updateWatchList.symbols)
        return try {
            DatabaseFactory.dbQuery {
                WatchList.update({
                    userId eq userUid and (watchListName eq updateWatchList.watchListName)
                }) {
                    it[updatedAt] = Instant.now().toString()
                    it[symbols] = symbol
                }>0
            }
        }catch (e:ExposedSQLException){
            throw CommonException("Unable to Update WatchLis                                                        t", HttpStatusCode.InternalServerError)
        }
    }


    override suspend fun deleteWatchList(deleteWatchlist: DeleteWatchlist,uuid: String): Boolean {

        val userUid=UUID.fromString(uuid)
        return try {
            DatabaseFactory.dbQuery {
//                RecentWatchList.deleteWhere { WatchList.userId eq userUid and (watchListName eq deleteWatchlist.watchlistName!!) }

                WatchList.update({
                    userId eq userUid and (watchListName eq deleteWatchlist.watchlistName!!)
                }) {
                    it[is_delete] = true
                }
            }>0
        }catch (e:ExposedSQLException){
            throw CommonException("Unable to delete WatchList", HttpStatusCode.InternalServerError)
        }
    }
    override suspend fun getAllWatchList(uuid: String): List<RecentWatchListResponse> {
        val userUid = UUID.fromString(uuid)
        return try {
            DatabaseFactory.dbQuery {
                WatchList.slice(WatchList.symbols,WatchList.watchListName)
                    .select(WatchList.userId eq userUid and (WatchList.is_delete eq false))
                    .map {
                        RecentWatchListResponse(it[watchListName]!! , Json.decodeFromString(it[WatchList.symbols]!!) )
                    }
            }
        }catch (e:Exception){
            throw CommonException("Unable to Fetch  WatchList", HttpStatusCode.InternalServerError)
        }
    }



}