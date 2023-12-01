package com.example.utils

import com.example.config.DBConfig
import com.example.database.table.RecentWatchList
import com.example.database.table.User
import com.example.database.table.WatchList
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction


object H2Database {
    private var database: Database? = null

    fun initializeTestDBForService() {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=create domain if not exists jsonb as other;"
        config.maximumPoolSize = 2
        config.isAutoCommit = true
        config.username = "postgres"
        config.password = "root"
        config.validate()
        val source = HikariDataSource(config)
        database = Database.connect(source)
        transaction(database){
            SchemaUtils.create(User,WatchList,RecentWatchList)
        }

    }

    fun createTestTable(vararg tables: Table) {
        transaction {
            tables.forEach {
                SchemaUtils.create(it)
            }
        }
    }
    fun dropTestTable(vararg tables: Table) {
        transaction {
            tables.forEach {
                SchemaUtils.drop(it)
            }
        }
    }



//    config.driverClassName = "org.h2.Driver"
//    config.jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=create domain if not exists jsonb as other;"
//    config.maximumPoolSize = 2
//    config.isAutoCommit = true
//    config.username = "postgres"
//    config.password = "root"
//    config.validate()

    fun init(): Database {
        return  Database.connect(
            url = DBConfig.urlTest,
            driver = DBConfig.driver,
            user = DBConfig.user,
            password = DBConfig.password
        )

    }
}