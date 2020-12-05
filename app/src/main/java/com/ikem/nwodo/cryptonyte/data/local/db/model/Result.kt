package com.ikem.nwodo.cryptonyte.data.local.db.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result (

        @SerializedName("data")
    @Expose
    var data: Data,

        @SerializedName("status")
    @Expose
    var status: String? = null
)