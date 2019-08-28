package com.ikem.nwodo.cryptonyte.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Coin::class], version = 1)
@TypeConverters(CoinTypeConverters::class)
abstract class CoinDatabase : RoomDatabase(){
    abstract fun coinDao(): CoinDao
}