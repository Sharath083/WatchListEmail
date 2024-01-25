package com.example.redis
import redis.clients.jedis.Jedis

class RedisClientBuilder(
    private val host: String,
    private val port: Int,

) {
    fun build(): Jedis {
        val jedis = Jedis(host, port)
        jedis.connection.setTimeoutInfinite();
        return jedis;
    }
}
