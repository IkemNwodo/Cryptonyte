package com.ikem.nwodo.cryptonyte.repository

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.db.model.*
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.ui.list.RateLimiter
import com.ikem.nwodo.cryptonyte.utils.NetworkBoundResource
import com.ikem.nwodo.cryptonyte.utils.Resource
import com.ikem.nwodo.cryptonyte.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinListRepository @Inject constructor(
        private val coinListDao: CoinDao,
        private val coinService: CoinService
) {
    fun loadCoins(): Flow<Resource<List<Coin>>> {
        return object : NetworkBoundResource<List<Coin>, Result>() {
            override suspend fun saveCallResult(item: Result) {
                item.data.coins?.let { coinListDao.insertCoins(it) }
            }

            override fun loadFromDb(): Flow<List<Coin>> {
                return coinListDao.loadCoins()
            }

            override fun shouldLoadDb(): Boolean {
                TODO("Not yet implemented")
            }

            override suspend fun createCall(): Flow<Resource<Result>> = withContext(Dispatchers.IO){
                val result = coinService.cryptoCurrencies()
                result.collect { resource ->
                    when(resource.status){
                         Status.LOADING -> { }
                         Status.SUCCESS -> {
                             resource.data?.data?.coins?.map { async { fetchCoins(it) } }
                                     ?.awaitAll()
                         }
                    }

                }

                return
            }

        }.asFlow()
    }

    private fun fetchCoins(coin: Coin) {
        val response = coinService.getCoinHistory24h()
    }

    fun fetchCoinsWithHistory() = flow<Resource<List<Coin>>> {

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
