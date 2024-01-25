package com.example.plugins

import com.example.exceptions.CommonException
import com.example.redis.RedisSessionStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import org.koin.ktor.ext.inject

data class UserSession(val accountId: String) : Principal

fun Application.configureSecurity() {
    val redisSessionStorage by inject<RedisSessionStorage>()

    install(Sessions) {
        val secretHexEncryptKey = hex("00112233445566778899aabbccddeeff")
        val secretHexSignKey = hex("6819b57a326945c1968f45236589")

        cookie<UserSession>("userSession", redisSessionStorage) {
            transform(SessionTransportTransformerEncrypt(secretHexEncryptKey, secretHexSignKey))
        }
    }
    install(Authentication) {
        session<UserSession>("AUTH_SESSION") {
            validate { session ->
                if (session.accountId.isNotEmpty()) {
                    session
                } else {
                    null
                }
            }
            challenge {
                throw CommonException(
                    "WatchlistConstants.INVALID_SESSION",
                    HttpStatusCode.Unauthorized
                )
            }
        }
    }


}
