package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class WatchlistData(val watchListName:String, val symbols: List<Symbol>)
