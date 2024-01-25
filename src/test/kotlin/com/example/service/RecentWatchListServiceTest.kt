package com.example.service

import com.example.utils.H2Database
import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import com.example.di.myModule
import com.example.service.RecentWatchListService
import com.example.utils.TestInputs
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import java.sql.Connection
import kotlin.test.assertTrue

class RecentWatchListServiceTest {
    private val recentWatchListService = RecentWatchListService()
    private lateinit var database: Database

    @Before
    fun setup() {
        startKoin {
            modules(myModule)
        }
        database = H2Database.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database) {
            SchemaUtils.create(User, WatchList, RecentWatchList)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        transaction(database) {
            SchemaUtils.create(User, WatchList, RecentWatchList)
        }
    }
    @Test
    fun getRecentWatchListServiceTest():Unit= runBlocking {
        recentWatchListService.getRecentWatchListService("TestInputs.recentWatchlistData").apply {
            assertTrue(this.symbol.isNotEmpty())
        }
    }
    @Test
    fun getAllWatchListServiceTest():Unit= runBlocking {
        recentWatchListService.getAllWatchListService("TestInputs.recentWatchlistData").apply {
            assertTrue(this.isNotEmpty())
        }
    }
}