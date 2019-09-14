package com.ikem.nwodo.cryptonyte.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.Data

@Database(entities = [Coin::class, Data::class], version = 1)
@TypeConverters(CoinTypeConverters::class)
abstract class CoinDatabase : RoomDatabase(){
    abstract fun coinDao(): CoinDao
}