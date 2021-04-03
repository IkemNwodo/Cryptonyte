package com.ikem.data.repositories

import com.ikem.nwodo.cryptonyte.data.local.db.CoinDao
import com.ikem.nwodo.cryptonyte.data.remote.network.api.CoinService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinDetailRepository @Inject constructor(
        private val coinDao: CoinDao,
        private val coinService: CoinService
) : Repository {


    /*fun loadSingleCoin(id: Int): Flow<Resource<Coin>> {
        return object : NetworkBoundResource<Coin, Result>() {

            override suspend fun saveCallResult(item: Result) {}

            override fun shouldLoadDb(): Boolean {
                TODO("Not yet implemented")
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

            override fun shouldLoadDb(): Boolean {
                TODO("Not yet implemented")
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

            override fun shouldLoadDb(): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<Resource<Result>> {
                return coinService.getCoinHistory24h(id)
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }
*/
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
