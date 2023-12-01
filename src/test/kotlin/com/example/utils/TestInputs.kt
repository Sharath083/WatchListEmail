package com.example.utils

import com.example.model.*
import com.example.repository.UserDaoImpl
//import com.example.repository.UserDaoImplTest
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
    val sessionId="""7b45201925a5e182672316d17cb5d8d8%2F04f12a576fe070425f4a7822b4d1476c7
        |975ab264d72d1d233df39ba0f7c68557c5e018825a77453e8e080ac43bcdbbc%3Aef603
        |933872663b63f74ac7d3d545056e464e3d04f79aa79e02857df474f5d8b""".trimMargin()
    val userSession="userSession"
    val watchListName="sample1"

    val userRegisterDetails=UserRegistration("sharath0","sharath0@gmail.com","123456")
    const val userId= "0c70cadc-a8c9-4381-84b6-3ee0ab79c3b4"

    val watchlistData= WatchlistData("sample55", symbols)
    const val userUuid= "fc51e172-a45e-401d-8c47-c1bcf2d01c6d"
    val userRegisterDetailsInvalid=UserRegistration("sharath","sharath@iogmail.com","123456")

    val updateWatchList = UpdateWatchList(watchListName, updatedSymbols)
    val deleteWatchlist=DeleteWatchlist(watchListName)
    val recentWatchlistData=RecentWatchlistData(userId)

}