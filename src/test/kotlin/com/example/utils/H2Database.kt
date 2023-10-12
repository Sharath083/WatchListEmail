package com.example.utils

import com.example.config.DBConfig
import org.jetbrains.exposed.sql.Database


object H2Database {
    fun init(): Database {
        return  Database.connect(
            url = DBConfig.urlTest,
            driver = DBConfig.driver,
            user = DBConfig.user,
            password = DBConfig.password
        )

    }
}