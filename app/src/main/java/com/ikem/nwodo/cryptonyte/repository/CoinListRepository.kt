package com.ikem.nwodo.cryptonyte.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.Data
import com.ikem.nwodo.cryptonyte.db.model.Result
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.utils.AppExecutors
import com.ikem.nwodo.cryptonyte.utils.NetworkBoundResource
import com.ikem.nwodo.cryptonyte.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinListRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val coinListDao: CoinDao,
        private val coinService: CoinService
) {
    fun loadCoins(): LiveData<Resource<List<Coin>>> {
        return object : NetworkBoundResource<List<Coin>, Result>(appExecutors) {

            override fun saveCallResult(item: Result) {
                item.data.coins?.let { coinListDao.insertCoins(it) }
            }

            override fun shouldFetch(data: List<Coin>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Coin>> {
                return coinListDao.loadCoins()
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.cryptoCurrencies()
            }

        }.asLiveData()
    }

    fun fetchCoinHistory(id: Int): LiveData<Resource<Data>> {
        return object : NetworkBoundResource<Data, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                coinListDao.insertCoinHistory(item.data)
            }

            override fun shouldFetch(data: Data?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<Data> {
                return coinListDao.loadCoinHistory(id)
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.getCoinHistory24h(id)
            }

        }.asLiveData()
    }

}