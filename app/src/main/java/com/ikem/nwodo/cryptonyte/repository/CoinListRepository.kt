package com.ikem.nwodo.cryptonyte.repository

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.db.model.*
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.ui.list.RateLimiter
import com.ikem.nwodo.cryptonyte.utils.NetworkBoundResource
import com.ikem.nwodo.cryptonyte.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinListRepository @Inject constructor(
        private val coinListDao: CoinDao,
        private val coinService: CoinService
) {
    private fun loadCoins(): Flow<Resource<List<Coin>>> {
        return object : NetworkBoundResource<List<Coin>, Result>() {
            override suspend fun saveCallResult(item: Result) {
                item.data.coins?.let { coinListDao.insertCoins(it) }
            }

            override fun loadFromDb(): Flow<List<Coin>> {
                return coinListDao.loadCoins()
            }

            override fun shouldFetchFromRemote(item: List<Coin>): Boolean {
                return item.isEmpty()
            }

            override suspend fun createCall(): Flow<Resource<Result>> {
                return coinService.cryptoCurrencies()
            }

        }.asFlow()
    }

    private fun fetchCoinHistory(id: Int): Flow<Resource<CoinHistory24H>> {
        return object : NetworkBoundResource<CoinHistory24H, Result>() {
            override suspend fun saveCallResult(item: Result) {
                val coinHistory24H = CoinHistory24H(item.data.coinHistory, id)
                coinListDao.insertCoinHistory(coinHistory24H)
            }

            override fun loadFromDb(): Flow<CoinHistory24H> {
                return coinListDao.loadCoinHistory24H(id)
            }

            override fun shouldFetchFromRemote(item: CoinHistory24H): Boolean {
                return item.history.isEmpty()
            }

            override suspend fun createCall(): Flow<Resource<Result>> {
               return coinService.getCoinHistory24h(id)
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

    val fetchCoinsWithHistory = flow<Resource<List<Coin>>> {

        emit(Resource.loading())

        val coins = loadCoins().first().data
        coins?.map {
            val history24H= fetchCoinHistory(it.id).first().data!!
            it.history = history24H
            it
        }
        emit(Resource.success(coins))
    }
}
