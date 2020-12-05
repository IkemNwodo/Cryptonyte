package com.ikem.nwodo.cryptonyte.data.local.db.model


interface CoinHistory {
    val history: List<History>
    val id: Int
}
