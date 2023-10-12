package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class DeleteWatchlist(val userId: String?, val watchlistId: String?)
