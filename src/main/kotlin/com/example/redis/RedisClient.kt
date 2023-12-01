package com.example.redis

import com.example.redis.RedisClientBuilder
import redis.clients.jedis.Jedis

object RedisClient {
    val jedis: Jedis = RedisClientBuilder("localhost",6379).build()
}
