package com.ikem.nwodo.cryptonyte.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ikem.nwodo.cryptonyte.model.AllTimeHigh
import com.ikem.nwodo.cryptonyte.model.Social

/**
 * @author Ikem Nwodo
 * @since 3/11/2018
 */

@Entity
data class Coin @JvmOverloads constructor(@PrimaryKey
                                          @SerializedName("id")
                                          @Expose
                                          val id: Int? = null) {

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("symbol")
    @Expose
    var symbol: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("color")
    @Expose
    var color: String? = null
    @SerializedName("iconType")
    @Expose
    var iconType: String? = null
    @SerializedName("iconUrl")
    @Expose
    var iconUrl: String? = null
    @SerializedName("websiteUrl")
    @Expose
    var websiteUrl: String? = null
    @SerializedName("socials")
    @Expose
    var socials: List<Social>? = null
    @SerializedName("confirmedSupply")
    @Expose
    var confirmedSupply: Boolean? = null
    @SerializedName("numberOfMarkets")
    @Expose
    var numberOfMarkets: Int? = null
    @SerializedName("numberOfExchanges")
    @Expose
    var numberOfExchanges: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("volume")
    @Expose
    var volume: Int? = null
    @SerializedName("marketCap")
    @Expose
    var marketCap: Int? = null
    @SerializedName("price")
    @Expose
    var price: String? = null
    @SerializedName("circulatingSupply")
    @Expose
    var circulatingSupply: Int? = null
    @SerializedName("totalSupply")
    @Expose
    var totalSupply: Int? = null
    @SerializedName("approvedSupply")
    @Expose
    var approvedSupply: Boolean? = null
    @SerializedName("firstSeen")
    @Expose
    var firstSeen: Int? = null
    @SerializedName("change")
    @Expose
    var change: Double? = null
    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("history")
    @Expose
    var history: List<String>? = null
    @SerializedName("allTimeHigh")
    @Expose
    @Embedded
    var allTimeHigh: AllTimeHigh? = null
    @SerializedName("penalty")
    @Expose
    var penalty: Boolean? = null

}
