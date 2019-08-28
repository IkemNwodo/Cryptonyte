package com.ikem.nwodo.cryptonyte.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class AllTimeHigh {

    @SerializedName("price")
    @Expose
    @ColumnInfo(name = "all_time_high_price")
     var price : String? = null
    @SerializedName("timestamp")
    @Expose
    var timestamp : Int? = null
}