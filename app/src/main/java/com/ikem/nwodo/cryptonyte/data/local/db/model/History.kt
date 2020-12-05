package com.ikem.nwodo.cryptonyte.data.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class History(

    @SerializedName("price")
    @Expose
    val price: String,

    @SerializedName("timestamp")
    @Expose
    val timestamp: Long,

    @PrimaryKey(autoGenerate = true)
    val history_id: Int = 0
)
