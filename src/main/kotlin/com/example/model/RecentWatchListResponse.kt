package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class RecentWatchListResponse(val watchListName:String,val symbol:List<Symbol>)