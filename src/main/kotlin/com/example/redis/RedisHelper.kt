package com.example.redis

import com.example.exceptions.CommonException
import com.example.utils.appconstants.InfoMessage
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import redis.clients.jedis.Jedis

fun ApplicationCall.getRequestHeader(key: String): String? {
    return this.request.headers[key]?.trim()
}

fun ApplicationCall.setResponseHeader(key: String, value: String) {
    this.response.header(key, value)
}
fun ApplicationCall.getSessionParameters(): String {
    val sessionId = this.request.header("Header")?.trim()
    if (sessionId.isNullOrEmpty()) {
        throw CommonException("InfoMessage.INVALID_SESSION", HttpStatusCode.Unauthorized)
    } else {
        return sessionId
    }
}
class RedisHelper(val redisClient: Jedis) {

    fun set(key: String, value: Any) {
        val jsonString = Gson().toJson(value)
        redisClient.set(key, jsonString.trim())
    }
    fun getString(key: String): String? {
        return redisClient.get(key)
    }
}
