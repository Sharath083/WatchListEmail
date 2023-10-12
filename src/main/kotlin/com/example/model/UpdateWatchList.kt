package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateWatchList(val userId: String?,val watchListId: String?, val symbols: List<Symbol>)
