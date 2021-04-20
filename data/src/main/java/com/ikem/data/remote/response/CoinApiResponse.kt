package com.ikem.data.remote.response

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey

class CoinApiResponse(val data: Data, val status: String)


data class Data(val coins: List<Coin>?, val coinHistory: List<History>, val change: Double)

data class Coin(
        val id: Int,
        val uuid: String,
        val slug: String,
        val symbol: String,
        val name: String,
        val color: String? = null,
        val iconType: String,
        val iconUrl: String,
        val websiteUrl: String? = null,
        val confirmedSupply: Boolean,
        val numberOfMarkets: Long,
        val numberOfExchanges: Long,
        val type: String,
        val volume: Long,
        val marketCap: Long,
        val price: String,
        val circulatingSupply: Double,
        val totalSupply: Double,
        val approvedSupply: Boolean,
        val firstSeen: Long,
        val change: Double,
        val rank: Long,
        val allTimeHigh: AllTimeHigh,
        val penalty: Boolean,
        var histories: List<History>? = null
)

data class History(val price: String, val timestamp: Long)

class AllTimeHigh(var price : String? = null, var timestamp : Long = 0L)