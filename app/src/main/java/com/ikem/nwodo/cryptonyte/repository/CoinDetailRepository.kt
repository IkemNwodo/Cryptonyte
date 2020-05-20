package com.ikem.nwodo.cryptonyte.repository

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.db.model.*
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.ui.list.RateLimiter
import com.ikem.nwodo.cryptonyte.utils.AppExecutors
import com.ikem.nwodo.cryptonyte.utils.NetworkBoundResource
import com.ikem.nwodo.cryptonyte.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinDetailRepository @Inject constructor(
        private val coinDao: CoinDao,
        private val coinService: CoinService
) {


    fun loadSingleCoin(id: Int): Flow<Resource<Coin>> {
        return object : NetworkBoundResource<Coin, Result>() {

            override suspend fun saveCallResult(item: Result) {}

            override fun shouldFetchFromRemote(item: Coin): Boolean {
                return false
            }

            override fun loadFromDb(): Flow<Coin> {
                return coinDao.loadCoin(id)
            }

            override suspend fun createCall(): Flow<Resource<Result>> {
                return coinService.cryptoCurrencies()
            }

        }.asFlow()
    }

     fun loadCoins(): Flow<Resource<List<Coin>>> {
        return object : NetworkBoundResource<List<Coin>, Result>() {
            override suspend fun saveCallResult(item: Result) {
                item.data.coins?.let { coinDao.insertCoins(it) }
            }

            override fun loadFromDb(): Flow<List<Coin>> {
                return coinDao.loadCoins()
            }

            override fun shouldFetchFromRemote(item: List<Coin>): Boolean {
                return item.isEmpty()
            }

            override suspend fun createCall(): Flow<Resource<Result>> {
                return coinService.cryptoCurrencies()
            }

        }.asFlow()
    }

     fun fetchCoinHistory(id: Int): Flow<Resource<CoinHistory24H>> {
        return object : NetworkBoundResource<CoinHistory24H, Result>() {
            override suspend fun saveCallResult(item: Result) {
                val coinHistory24H = CoinHistory24H(item.data.coinHistory, id)
                coinDao.insertCoinHistory(coinHistory24H)
            }

            override fun loadFromDb(): Flow<CoinHistory24H> {
                return coinDao.loadCoinHistory24H(id)
            }

            override fun shouldFetchFromRemote(item: CoinHistory24H): Boolean {
                return item.history.isEmpty()
            }

            override suspend fun createCall(): Flow<Resource<Result>> {
                return coinService.getCoinHistory24h(id)
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

    /*fun fetchCoinHistory7d(id: Int) : LiveData<Resource<Data>>{
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
    }*/
}
