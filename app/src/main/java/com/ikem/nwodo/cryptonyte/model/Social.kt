package com.ikem.nwodo.cryptonyte.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Social{
    @SerializedName("name")
    @Expose
    var Socialname: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null
}