package com.ikem.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoins(coins: List<Coin>)

    @Query("SELECT * FROM coins_table")
    fun loadCoins(): Flow<List<Coin>>

    @Query("SELECT * FROM coins_table WHERE id= :id")
    fun loadCoin(id : Int): Flow<Coin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinHistory(coinHistory24H: CoinHistory24H)

    @Query("SELECT * FROM coins_history_24h WHERE id= :id")
    fun loadCoinHistory24H(id: Int): Flow<CoinHistory24H>
}