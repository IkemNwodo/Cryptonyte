package com.ikem.nwodo.cryptonyte.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCoins(coins: List<Coin>)

    @Query("SELECT * FROM coin")
    abstract fun loadCoins(): LiveData<List<Coin>>

    @Query("SELECT * FROM coin WHERE id= :id")
    abstract fun loadCoin(id : Int): LiveData<Coin>
}