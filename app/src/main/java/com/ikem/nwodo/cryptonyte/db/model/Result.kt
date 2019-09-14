package com.ikem.nwodo.cryptonyte.db.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("data")
    @Expose
    lateinit var data: Data

    @SerializedName("status")
    @Expose
    var status: String? = null
}