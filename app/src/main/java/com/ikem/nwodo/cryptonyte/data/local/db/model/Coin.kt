package com.ikem.nwodo.cryptonyte.data.local.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ikem.nwodo.cryptonyte.data.local.db.model.AllTimeHigh
import com.ikem.nwodo.cryptonyte.data.local.db.model.Social

/**
 * @author Ikem Nwodo
 * @since 3/11/2018
 */

@Entity(tableName = "coins_table")
data class Coin(

        @PrimaryKey
        @SerializedName("id")
        @Expose
        val id: Int,

        @SerializedName("uuid")
        @Expose
        val uuid: String,

        @SerializedName("slug")
        @Expose
        val slug: String,

        @SerializedName("symbol")
        @Expose
        val symbol: String,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("color")
        @Expose
        val color: String? = null,

        @SerializedName("iconType")
        @Expose
        val iconType: String,

        @SerializedName("iconUrl")
        @Expose
        val iconUrl: String,

        @SerializedName("websiteUrl")
        @Expose
        val websiteUrl: String? = null,

        @SerializedName("socials")
        @Expose
        val socials: List<Social>,

        @SerializedName("confirmedSupply")
        @Expose
        val confirmedSupply: Boolean,

        @SerializedName("numberOfMarkets")
        @Expose
        val numberOfMarkets: Long,

        @SerializedName("numberOfExchanges")
        @Expose
        val numberOfExchanges: Long,

        @SerializedName("type")
        @Expose
        val type: String,

        @SerializedName("volume")
        @Expose
        val volume: Long,

        @SerializedName("marketCap")
        @Expose
        val marketCap: Long,

        @SerializedName("price")
        @Expose
        val price: String,

        @SerializedName("circulatingSupply")
        @Expose
        val circulatingSupply: Double,

        @SerializedName("totalSupply")
        @Expose
        val totalSupply: Double,

        @SerializedName("approvedSupply")
        @Expose
        val approvedSupply: Boolean,

        @SerializedName("firstSeen")
        @Expose
        val firstSeen: Long,

        @SerializedName("change")
        @Expose
        val change: Double,

        @SerializedName("rank")
        @Expose
        val rank: Long,

        @SerializedName("allTimeHigh")
        @Expose
        @Embedded
        val allTimeHigh: AllTimeHigh,

        @SerializedName("penalty")
        @Expose
        val penalty: Boolean
){
    @Ignore
    lateinit var history: CoinHistory24H

}
