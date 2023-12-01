//package com.example.repository
//
//import com.example.utils.H2Database
//import com.example.database.table.RecentWatchList
//import com.example.database.table.User
//import com.example.database.table.WatchList
//import com.example.repository.RecentWatchListDaoImpl
//import kotlinx.coroutines.runBlocking
//import org.jetbrains.exposed.sql.Database
//import org.jetbrains.exposed.sql.SchemaUtils
//import org.jetbrains.exposed.sql.transactions.TransactionManager
//import org.jetbrains.exposed.sql.transactions.transaction
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import java.sql.Connection
//
//import kotlin.test.assertEquals
//import kotlin.test.assertTrue
//
//class RecentWatchListDaoImplTest {
//    private val recentWatchListDaoImpl = RecentWatchListDaoImpl()
//    private lateinit var database: Database
//
//    @Before
//    fun setup() {
//        database = H2Database.init()
//        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
//        transaction(database) {
//            SchemaUtils.create(User, WatchList, RecentWatchList)
//        }
//    }
//
//    @After
//    fun tearDown() {
//        transaction(database) {
//            SchemaUtils.create(User, WatchList, RecentWatchList)
//        }
//    }
//
//    @Test
//    fun getRecentWatchList(): Unit = runBlocking {
//        recentWatchListDaoImpl.getRecentWatchList(recentWatchlistData).apply {
//            assertEquals(this.watchListName, "List0")
//        }
//    }
//
//    @Test
//    fun getAllWatchListTest(): Unit = runBlocking {
//        recentWatchListDaoImpl.getAllWatchList(recentWatchlistData).apply {
//            assertTrue(this.isNotEmpty())
//        }
//    }
//}
