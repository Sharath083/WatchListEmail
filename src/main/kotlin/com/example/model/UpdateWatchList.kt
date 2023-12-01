package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateWatchList(val watchListName: String, val symbols: List<Symbol>)
