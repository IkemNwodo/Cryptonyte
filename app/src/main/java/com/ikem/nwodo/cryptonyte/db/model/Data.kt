package com.ikem.nwodo.cryptonyte.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Data(

        @SerializedName("coins")
        @Expose
        val coins: List<Coin>,

        @SerializedName("history")
        @Expose
        val coinHistory: List<History>,

        @SerializedName("change")
        @Expose
        val change: Double,

        @PrimaryKey(autoGenerate = true)
        val historyId: Int
)
