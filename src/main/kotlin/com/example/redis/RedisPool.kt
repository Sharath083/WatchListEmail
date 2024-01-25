package com.example.redis

import redis.clients.jedis.DefaultJedisClientConfig
import redis.clients.jedis.HostAndPort
import redis.clients.jedis.JedisPool


class RedisPool: JedisPool( HostAndPort("localhost",6379),
    DefaultJedisClientConfig.builder().ssl(false).
    user("test").password("test").build()
) {
    init {
        "test".let {
            this.resource.use { jedis ->
                jedis.auth("test", "test")
            }
        }
//        this.maxTotal = env.redisMaxConnections
//        this.maxIdle = env.redisMaxIdleConnections
//        this.minIdle = env.redisMinIdleConnections
//        this.testOnBorrow = true
    }
}