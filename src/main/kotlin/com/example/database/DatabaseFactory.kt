package com.example.database

import com.example.config.EnvironmentVariables
import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    private val env=EnvironmentVariables.env

    fun init() {
        Database.connect(
            url = env.connectionUrl,
            driver = env.driverClassName,
            user = env.username,
            password = env.password
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


