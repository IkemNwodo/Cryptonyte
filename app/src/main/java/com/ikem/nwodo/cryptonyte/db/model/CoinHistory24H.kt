package com.ikem.nwodo.cryptonyte.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins_history_24h")
data class CoinHistory24H(val history: List<History>, @PrimaryKey val id: Int)
