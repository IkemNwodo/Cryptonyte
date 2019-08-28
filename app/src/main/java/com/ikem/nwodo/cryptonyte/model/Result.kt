package com.ikem.nwodo.cryptonyte.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ikem.nwodo.cryptonyte.model.Data

class Result {

    @SerializedName("data")
    @Expose
    lateinit var data: Data

    @SerializedName("status")
    @Expose
    var status: String? = null
}