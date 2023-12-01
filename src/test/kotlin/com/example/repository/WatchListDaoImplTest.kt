package com.example.repository

import com.example.dao.WatchListDao
import com.example.utils.H2Database
import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import com.example.model.WatchlistData
import com.example.redis.RedisHelper
import com.example.redis.RedisPool
import com.example.redis.RedisSessionStorage
import com.example.repository.WatchListDaoImpl
import com.example.utils.MockObject
import com.example.utils.TestInputs.symbols
import com.example.utils.TestInputs.userId
import com.example.utils.TestInputs.watchlistData
import io.ktor.server.config.*
import io.ktor.server.testing.*
import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.awt.SystemTray
import java.rmi.Naming.bind
import java.sql.Connection
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WatchListDaoImplTest {
    companion object {
        val watchListDaoImpl = WatchListDaoImpl()
        lateinit var uuid:String

        @BeforeClass
        @JvmStatic
        fun setup() {
            H2Database.initializeTestDBForService()
            val testModule = module {
                singleOf(::WatchListDaoImpl) { bind<WatchListDao>() }
            }
            startKoin {
                modules(testModule)
            }
            uuid=MockObject.userEntityMock()
//            MockObject.watchListZEntityMock()

        }

        @JvmStatic
        @AfterClass
        fun tearDown() {
            stopKoin()
        }
    }

    @Test
    fun createWatchListTest(){
        runBlocking {
            watchListDaoImpl.createWatchList(WatchlistData("sam", symbols = symbols),uuid,).apply {
                assertEquals(this.toString().length,36)
            }
        }
    }


}

//    companion object {
//
//        private lateinit var database: Database
//        private lateinit var redisMock: RedisPool
//
//        private val watchListDaoImpl = WatchListDaoImpl()
//
//        @BeforeClass
//        @JvmStatic
//        fun setUp() {
//            createTestEnvironment {
//                config = ApplicationConfig("test.conf")
////            Configuration.initConfig(this.build { })
////            SharedConfiguration.initConfig(this.build { })
//            }
//            MockKAnnotations.init(this)
//            redisMock = RedisMockSetup.createRedisMock()
//            val testModule = module {
//                singleOf(::WatchListDaoImpl) {bind<WatchListDao>()}
////                single<RedisSessionStorage> { RedisSessionStorage(redisMock) }
////                single<RedisHelper> { RedisHelper(redisMock) }
//            }
//            startKoin {
//                modules(testModule)
//            }
//
//
//        }
//    }
//    @Before
//    fun setup() {
//        database = H2Database.init()
//        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
//        transaction  (database) {
//            SchemaUtils.create(User,WatchList,RecentWatchList)
//        }
//    }
//
//    @After
//    fun tearDown() {
//        transaction(database) {
//            SchemaUtils.create(User,WatchList,RecentWatchList)
//        }
//    }
//
//
//    @Test
//    fun createWatchListTest(): Unit= runBlocking {
//        watchListDaoImpl.createWatchList(watchlistData).apply {
//            assertEquals(this.toString().length,36)
//        }
//    }
//    @Test
//    fun updateWatchListTest():Unit= runBlocking{
//        watchListDaoImpl.updateWatchList(updateWatchList).apply {
//            assertTrue(this)
//        }
//    }
//    @Test
//    fun deleteWatchListTest():Unit= runBlocking{
//        watchListDaoImpl.deleteWatchList(deleteWatchlist).apply {
//            assertTrue(this)
//        }
//    }
//
//}