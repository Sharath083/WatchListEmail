package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class WatchlistData(val userId: String?,val watchListName:String, val symbols: List<Symbol>)
