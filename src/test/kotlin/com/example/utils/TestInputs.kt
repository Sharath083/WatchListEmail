package com.example.utils

import com.example.model.*
import com.example.repository.UserDaoImpl
import com.example.repository.UserDaoImplTest
import com.example.repository.WatchListDaoImpl
import kotlinx.coroutines.runBlocking
import java.util.UUID

object TestInputs {
    val symbols = listOf(
        Symbol(
            asset = "TestAdd",
            strike = 0.0,
            lotSize = 1,
            tickSize = 0.05,
            streamSym = "21238_NSE",
            instrument = "STK",
            multiplier = 1.0,
            displayName = "STK_AUBANK_EQ_NSE",
            companyName = "Bank",
            expiry = "2024-12-31",
            optionChain = false,
            symbolTag = "AUBANK",
            sector = "Banks",
            exchange = "NSE",
            isIn = "INE949L01017",
            baseSymbol = "AUBANK"
        )
    )
    val updatedSymbols = listOf(
        Symbol(
            asset = "TestUpdate",
            strike = 0.0,
            lotSize = 1,
            tickSize = 0.05,
            streamSym = "21238_NSE",
            instrument = "STK",
            multiplier = 1.0,
            displayName = "STK_AUBANK_EQ_NSE",
            companyName = "Bank",
            expiry = "2024-12-31",
            optionChain = false,
            symbolTag = "AUBANK",
            sector = "Banks",
            exchange = "NSE",
            isIn = "INE949L01017",
            baseSymbol = "AUBANK"
        )
    )

    val userRegisterDetails=UserRegistration("sharath0","sharath0@gmail.com","123456")
    private const val userId= "0c70cadc-a8c9-4381-84b6-3ee0ab79c3b4"

    val watchlistData= WatchlistData(userId,"List0", symbols)
    private const val watchListId= "fc51e172-a45e-401d-8c47-c1bcf2d01c6d"
    val userRegisterDetailsInvalid=UserRegistration("i","js@gmail.com","123456")

    val updateWatchList = UpdateWatchList(userId, watchListId, updatedSymbols)
    val deleteWatchlist=DeleteWatchlist(userId, watchListId)
    val recentWatchlistData=RecentWatchlistData(userId)

}