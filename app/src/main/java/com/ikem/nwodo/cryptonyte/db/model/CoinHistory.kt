package com.ikem.nwodo.cryptonyte.db.model


interface CoinHistory {
    val history: List<History>
    val id: Int
}
