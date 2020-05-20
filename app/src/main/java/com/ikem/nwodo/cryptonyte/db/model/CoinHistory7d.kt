package com.ikem.nwodo.cryptonyte.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinHistory7d(val history: List<History>, @PrimaryKey val id: Int)
