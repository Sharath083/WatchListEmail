package com.example.config

import io.ktor.server.application.*


data class WatchListConfigParameters(
    val driverClassName:String,
    val connectionUrl:String,
    val username:String,
    val password:String,
    val adminEmail:String,
    val adminPassword:String,
    val redisHost:String,
    val redisPort:Int,
)
object EnvironmentVariables {
    lateinit var env: WatchListConfigParameters
    fun getEnv(environment: ApplicationEnvironment) {
        env= WatchListConfigParameters(
            driverClassName = environment.config.property("database.driverClassName").getString(),
            connectionUrl = environment.config.property("database.connectionUrl").getString(),
            username = environment.config.property("database.username").getString(),
            password = environment.config.property("database.password").getString(),
            adminEmail = environment.config.property("email.adminEmail").getString(),
            adminPassword = environment.config.property("email.adminPassword").getString(),
            redisHost = environment.config.property("redis.host").getString(),
            redisPort = environment.config.property("redis.port").getString().toInt()
        )



    }

}
