package com.ikem.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Coin::class, CoinHistory24H::class], version = 1, exportSchema = false)
@TypeConverters(CoinTypeConverters::class)
abstract class CoinDatabase : RoomDatabase(){
    abstract fun coinDao(): CoinDao

    companion object {
        const val DATABASE_NAME = "coin_database"
    }
}
