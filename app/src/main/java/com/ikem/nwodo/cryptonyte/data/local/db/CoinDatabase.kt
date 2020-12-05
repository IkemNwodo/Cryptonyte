package com.ikem.nwodo.cryptonyte.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.data.local.db.model.CoinHistory24H
import com.ikem.nwodo.cryptonyte.data.local.db.model.Data
import com.ikem.nwodo.cryptonyte.data.local.db.model.History

@Database(entities = [Coin::class, CoinHistory24H::class], version = 1)
@TypeConverters(CoinTypeConverters::class)
abstract class CoinDatabase : RoomDatabase(){
    abstract fun coinDao(): CoinDao
}
