package com.example.redis

import com.example.config.EnvironmentVariables
import com.example.redis.RedisClientBuilder
import redis.clients.jedis.Jedis

object RedisClient {
    private val env=EnvironmentVariables.env
    val jedis: Jedis = RedisClientBuilder(env.redisHost,env.redisPort).build()
}
