package com.example.service

import com.example.exceptions.CommonException
import com.example.model.DeleteWatchlist
import com.example.model.SuccessResponse
import com.example.model.UpdateWatchList
import com.example.model.WatchlistData
import com.example.repository.WatchListDaoImpl
import io.ktor.http.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WatchListService:KoinComponent {
    private val watchListDaoImpl by inject<WatchListDaoImpl>()


    suspend fun createWatchListService(watchlistData: WatchlistData,uuid:String):SuccessResponse{
        try {

            val sameWatchlistCount = watchListDaoImpl
                .getWatchlistCountAndSameNameWatchlistCount(uuid, watchlistData.watchListName)



            if (sameWatchlistCount > 0) {
                throw CommonException(
                    "InfoMessage.WATCHLIST_NAME_EXISTS",
                    HttpStatusCode.Conflict
                )

            }
            watchListDaoImpl.createWatchList(watchlistData,uuid)
                return SuccessResponse("WatchList Created", HttpStatusCode.Created.toString())

        }catch (e:ExposedSQLException){
            throw CommonException(msg ="DATABASE_EXPOSED_SQL_ERROR",HttpStatusCode.InternalServerError)
        }


    }
    suspend fun updateWatchListService(updateWatchList: UpdateWatchList,uuid: String):SuccessResponse{
        try {

            val sameWatchlistCount = watchListDaoImpl
                .getWatchlistCountAndSameNameWatchlistCount(uuid, updateWatchList.watchListName)

            if (sameWatchlistCount == 0) {
                throw CommonException(
                    "InfoMessage.WATCHLIST_NOT_FOUND",
                    HttpStatusCode.Conflict
                )
            }
            watchListDaoImpl.updateWatchList(updateWatchList,uuid)
            return SuccessResponse("WatchList Updated",HttpStatusCode.Created.toString())

        }catch (e:ExposedSQLException){
            throw CommonException(msg ="DATABASE_EXPOSED_SQL_ERROR",HttpStatusCode.InternalServerError)
        }

    }
    suspend fun deleteWatchList(deleteWatchlist: DeleteWatchlist,uuid: String):SuccessResponse{
        try {
            val delete=watchListDaoImpl.deleteWatchList(deleteWatchlist,uuid)
            return when(delete){
                true-> SuccessResponse("WatchList Deleted", HttpStatusCode.Created.toString())
                else->
                    throw CommonException(msg ="WatchList ${deleteWatchlist.watchlistName} does not exists",HttpStatusCode.InternalServerError)
            }
        }catch (e:ExposedSQLException){
            throw CommonException(msg ="DATABASE_EXPOSED_SQL_ERROR",HttpStatusCode.InternalServerError)
        }


    }


}