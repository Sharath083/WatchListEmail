package com.example.config

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.HoconApplicationConfig

object DBConfig {
    private val config = HoconApplicationConfig(ConfigFactory.load())
    val url = config.property("database.url").getString()
    val urlTest = config.property("database.urlTest").getString()
    val driver = config.property("database.driver").getString()
    val user = config.property("database.user").getString()
    val password = config.property("database.password").getString()
}
