package com.example.redis

import com.example.exceptions.CommonException
import com.example.utils.appconstants.InfoMessage
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import org.koin.java.KoinJavaComponent.inject
import redis.clients.jedis.Jedis

fun ApplicationCall.getRequestHeader(key: String): String? {
    return this.request.headers[key]?.trim()
}

fun ApplicationCall.setResponseHeader(key: String, value: String) {
    this.response.header(key, value)
}
fun ApplicationCall.getSession():Pair<String,String>{
    val session= this.request.headers["Session"]
    if(session.isNullOrEmpty()){
        throw CommonException("INVALID_SESSION", HttpStatusCode.Unauthorized)
    }
    else{
        return RedisHelper(RedisClient.jedis).validateSession(session)
    }
}
class RedisHelper(private val redisClient: Jedis) {

    fun set(key: String, value: Any) {
        val jsonString = Gson().toJson(value)
        redisClient.set(key, jsonString.trim())
    }
    private fun String.getString(): String? {
        return redisClient.get(this)
    }
    fun delete(key: String):Long {
        return redisClient.del(key)
    }


    fun validateSession(session: String):Pair<String,String>{
        val key=session.split("|")
        val s= key[0].getString()?.contains(key[1])
        if(s!=null){
            return Pair(key[0], key[1])
        }
        else{
            throw CommonException("INVALID_SESSION OR SESSION HAS EXPIRED", HttpStatusCode.Unauthorized)
        }
    }



}
