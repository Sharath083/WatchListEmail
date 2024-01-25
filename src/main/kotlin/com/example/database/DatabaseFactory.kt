package com.example.database


import com.example.config.DBConfig
import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    fun init() {
        Database.connect(
            url = DBConfig.url,
            driver = DBConfig.driver,
            user = DBConfig.user,
            password = DBConfig.password
        )
        transaction {
//            SchemaUtils.drop(User, WatchList, RecentWatchList)
            SchemaUtils.createMissingTablesAndColumns(User, WatchList, RecentWatchList)
        }
    }
    suspend fun <T> dbQuery(block: () -> T):T= newSuspendedTransaction(Dispatchers.IO){
        block()
    }
}


