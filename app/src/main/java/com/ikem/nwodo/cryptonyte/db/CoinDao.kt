package com.ikem.nwodo.cryptonyte.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.Data
import com.ikem.nwodo.cryptonyte.db.model.History

@Dao
abstract class CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCoins(coins: List<Coin>)

    @Query("SELECT * FROM coins_table")
    abstract fun loadCoins(): LiveData<List<Coin>>

    @Query("SELECT * FROM coins_table WHERE id= :id")
    abstract fun loadCoin(id : Int): LiveData<Coin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCoinHistory(histories: Data)

    @Query("SELECT * FROM  data WHERE historyId= :id")
    abstract fun loadCoinHistory(id: Int): LiveData<Data>
}