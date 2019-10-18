package com.ikem.nwodo.cryptonyte.repository

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.Data
import com.ikem.nwodo.cryptonyte.db.model.History
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.db.model.Result
import com.ikem.nwodo.cryptonyte.ui.list.RateLimiter
import com.ikem.nwodo.cryptonyte.utils.AppExecutors
import com.ikem.nwodo.cryptonyte.utils.NetworkBoundResource
import com.ikem.nwodo.cryptonyte.utils.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinDetailRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val coinDao: CoinDao,
        private val coinService: CoinService
) {
    private val coinDetailRateLimiter = RateLimiter<Int>(1, TimeUnit.MINUTES)
    fun loadCoins(): LiveData<Resource<List<Coin>>> {
        return object : NetworkBoundResource<List<Coin>, Result>(appExecutors) {

            override fun saveCallResult(item: Result) {
                item.data.coins?.let { coinDao.insertCoins(it) }
            }

            override fun shouldFetch(data: List<Coin>?): Boolean {
                return data == null || coinDetailRateLimiter.shouldFetch(0)
            }

            override fun loadFromDb(): LiveData<List<Coin>> {
                return coinDao.loadCoins()
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.cryptoCurrencies()
            }

        }.asLiveData()
    }

    fun fetchCoinHistory24h(id: Int) : LiveData<Resource<Data>>{
        return object : NetworkBoundResource<Data, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                val modify = item.data
                modify.timeFrame = "24h"
                modify.historyId = id
                coinDao.insertCoinHistory(modify)
            }

            override fun shouldFetch(data: Data?): Boolean {
                return data == null || coinDetailRateLimiter.shouldFetch(id)
            }

            override fun loadFromDb(): LiveData<Data> {
               return coinDao.loadCoinHistory(id, "24h")
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.getCoinHistory24h(id)
            }

        }.asLiveData()
    }

    fun fetchCoinHistory7d(id: Int) : LiveData<Resource<Data>>{
        return object : NetworkBoundResource<Data, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                val modify = item.data
                modify.timeFrame = "7d"
                modify.historyId = id
                coinDao.insertCoinHistory(modify)
            }

            override fun shouldFetch(data: Data?): Boolean {
                return data == null || coinDetailRateLimiter.shouldFetch(id)
            }

            override fun loadFromDb(): LiveData<Data> {
                return coinDao.loadCoinHistory(id, "7d")
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.getCoinHistory7d(id)
            }

        }.asLiveData()
    }

    fun fetchCoinHistory30d(id: Int) : LiveData<Resource<Data>>{
        return object : NetworkBoundResource<Data, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                val modify = item.data
                modify.timeFrame = "30d"
                modify.historyId = id
                coinDao.insertCoinHistory(modify)
            }

            override fun shouldFetch(data: Data?): Boolean {
                return data == null || coinDetailRateLimiter.shouldFetch(id)
            }

            override fun loadFromDb(): LiveData<Data> {
                return coinDao.loadCoinHistory(id, "30d")
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.getCoinHistory30d(id)
            }

        }.asLiveData()
    }

    fun fetchCoinHistory1y(id: Int) : LiveData<Resource<Data>>{
        return object : NetworkBoundResource<Data, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                val modify = item.data
                modify.timeFrame = "1y"
                modify.historyId = id
                coinDao.insertCoinHistory(modify)
            }

            override fun shouldFetch(data: Data?): Boolean {
                return data == null || coinDetailRateLimiter.shouldFetch(id)
            }

            override fun loadFromDb(): LiveData<Data> {
                return coinDao.loadCoinHistory(id, "1y")
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.getCoinHistory1y(id)
            }

        }.asLiveData()
    }




    fun loadSingleCoin(id: Int): LiveData<Resource<Coin>> {
        return object : NetworkBoundResource<Coin, Result>(appExecutors) {

            override fun saveCallResult(item: Result) {
                item.data.coins?.let { coinDao.insertCoins(it) }
            }

            override fun shouldFetch(data: Coin?): Boolean {
                return false
            }

            override fun loadFromDb(): LiveData<Coin> {
                return coinDao.loadCoin(id)
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.cryptoCurrencies()
            }

        }.asLiveData()
    }
}