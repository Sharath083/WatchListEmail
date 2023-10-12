package com.example.repository

import com.example.utils.H2Database
import com.example.utils.TestInputs.userRegisterDetails
import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import org.jetbrains.exposed.sql.Database
import kotlin.test.assertEquals

class UserDaoImplTest {
    private lateinit var database: Database
    val userDaoImpl=UserDaoImpl()

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
            SchemaUtils.create(User,WatchList,RecentWatchList)        }
    }
    @Test
    fun userRegistrationTest(): Unit = runBlocking{
        userDaoImpl.userRegistration(userRegisterDetails).apply {
            assertEquals(this.toString().length,36)
        }
    }

}