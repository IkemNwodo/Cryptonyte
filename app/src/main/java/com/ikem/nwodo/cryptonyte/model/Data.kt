package com.ikem.nwodo.cryptonyte.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ikem.nwodo.cryptonyte.db.Coin

import java.util.ArrayList

class Data {

    @SerializedName("coins")
    @Expose
    lateinit var coins: List<Coin>

    @SerializedName("history")
    @Expose
    lateinit var coinHistory: List<History>

    @SerializedName("change")
    @Expose
    private val change: Double? = null
}

