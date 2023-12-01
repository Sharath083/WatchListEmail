package com.example.repository

import com.example.dao.UserDao
import com.example.dao.WatchListDao
import com.example.utils.H2Database
import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import com.example.di.myModule
import com.example.entity.RecentWatchListEntity
import com.example.exceptions.CommonException
import com.example.model.LoginData
import com.example.model.UserRegistration
import com.example.repository.UserDaoImpl
import com.example.utils.MockObject
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import org.jetbrains.exposed.sql.Database
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserDaoImplTest {
    companion object {
        val userDaoImpl = UserDaoImpl()

        @BeforeClass
        @JvmStatic
        fun setup() {
            H2Database.initializeTestDBForService()
            val testModule = module {
                singleOf(::UserDaoImpl) {bind<UserDao>()}
            }
            startKoin {
                modules(testModule)
            }
            MockObject.userEntityMock()

        }

        @JvmStatic
        @AfterClass
        fun tearDown() {
            stopKoin()
        }
    }

    @Test
    fun createUser() {
        val createUserRequest = UserRegistration("sharath10","sharath10@gmail.com","123456")
        val result = runBlocking {
            userDaoImpl.userRegistration(
                createUserRequest
            )
        }
        assertEquals(36, result.toString().length)
    }

    @Test
    fun createWatchlistWithDbException() {

        runBlocking {
//            H2Database.dropTestTable(User,WatchList,RecentWatchList)
            val commonException = assertFailsWith(
                exceptionClass = CommonException::class,
                block = {
                    userDaoImpl.userRegistration(UserRegistration("sharath","sharath@gmail.com","123456"))
                },
            )
            assertEquals(HttpStatusCode.BadRequest, commonException.statusCode)
        }

        // re-creating the table
//        H2Database.createTestTable(User,WatchList,RecentWatchList)
    }

    @Test
    fun userCheckTest(){
        runBlocking {
            userDaoImpl.checkUser("sharath","sharath@gmail.com").apply {
                assertFalse { this }
            }
        }
    }
    @Test
    fun userCheckTestFail(){

        runBlocking {
            H2Database.dropTestTable(RecentWatchList,WatchList,User)

            val commonException= assertFailsWith (
                exceptionClass = CommonException::class,
                block= {
                    userDaoImpl.checkUser("sharath", "sharath@gmail.com")
                },
                )
            assertEquals(HttpStatusCode.BadRequest, commonException.statusCode)
        }
        H2Database.createTestTable(User,WatchList,RecentWatchList)
    }
    @Test
    fun loginCheckTest(){
        runBlocking {
            userDaoImpl.userLoginCheck(LoginData("sharath","123456")).apply {
                assertEquals(36,this.length)
            }
        }
    }
    @Test
    fun loginCheckTestFail(){

        runBlocking {
            H2Database.dropTestTable(RecentWatchList,WatchList,User)

            val commonException= assertFailsWith (
                exceptionClass = CommonException::class,
                block= {
                    userDaoImpl.userLoginCheck(LoginData("sharath","123456"))
                },
            )
            assertEquals(HttpStatusCode.BadRequest, commonException.statusCode)
        }
        H2Database.createTestTable(User,WatchList,RecentWatchList)
    }




}