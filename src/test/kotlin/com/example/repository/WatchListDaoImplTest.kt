package com.example.repository

import com.example.utils.H2Database
import com.example.utils.TestInputs.deleteWatchlist
import com.example.utils.TestInputs.updateWatchList
import com.example.utils.TestInputs.watchlistData
import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WatchListDaoImplTest  {
    private lateinit var database: Database

    private val watchListDaoImpl=WatchListDaoImpl()
    @Before
    fun setup() {
        database = H2Database.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction  (database) {
            SchemaUtils.create(User,WatchList,RecentWatchList)
        }
    }

    @After
    fun tearDown() {
        transaction(database) {
            SchemaUtils.create(User,WatchList,RecentWatchList)
        }
    }
    @Test
    fun createWatchListTest(): Unit= runBlocking {
        watchListDaoImpl.createWatchList(watchlistData).apply {
            assertEquals(this.toString().length,36)
        }
    }
    @Test
    fun updateWatchListTest():Unit= runBlocking{
        watchListDaoImpl.updateWatchList(updateWatchList).apply {
            assertTrue(this)
        }
    }
    @Test
    fun deleteWatchListTest():Unit= runBlocking{
        watchListDaoImpl.deleteWatchList(deleteWatchlist).apply {
            assertTrue(this)
        }
    }

}