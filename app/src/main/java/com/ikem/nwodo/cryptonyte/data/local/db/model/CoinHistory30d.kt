package com.ikem.nwodo.cryptonyte.data.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinHistory30d(val history: List<History>, @PrimaryKey val id: Int)
