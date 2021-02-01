package com.ikem.nwodo.cryptonyte.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.data.local.db.model.CoinHistory24H
import com.ikem.nwodo.cryptonyte.data.local.db.model.Data
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCoins(coins: List<Coin>)

    @Query("SELECT * FROM coins_table")
    abstract fun loadCoins(): Flow<List<Coin>>

    @Query("SELECT * FROM coins_table WHERE id= :id")
    abstract fun loadCoin(id : Int): Flow<Coin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCoinHistory(coinHistory24H: CoinHistory24H)

    @Query("SELECT * FROM coins_history_24h WHERE id= :id")
    abstract fun loadCoinHistory24H(id: Int): Flow<CoinHistory24H>
}
