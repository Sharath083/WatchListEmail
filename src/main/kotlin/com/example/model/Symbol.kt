package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Symbol(
    val asset: String?,
    val strike: Double?,
    val lotSize: Int?,
    val tickSize: Double?,
    val streamSym: String?,
    val instrument: String?,
    val multiplier: Double?,
    val displayName: String?,
    val companyName: String?,
    val expiry: String?,
    val optionChain: Boolean?,
    val symbolTag: String?,
    val sector: String?,
    val exchange: String?,
    val isIn: String?,
    val baseSymbol: String?
)
