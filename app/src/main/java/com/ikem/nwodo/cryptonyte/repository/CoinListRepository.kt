package com.ikem.nwodo.cryptonyte.repository

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.data.local.db.CoinDao
import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.data.local.db.model.CoinHistory24H
import com.ikem.nwodo.cryptonyte.data.local.db.model.Result
import com.ikem.nwodo.cryptonyte.data.remote.network.api.CoinService
import com.ikem.nwodo.cryptonyte.data.remote.source.coinList.CoinListLocalSource
import com.ikem.nwodo.cryptonyte.data.remote.source.coinList.CoinListRemoteSource_impl
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
        private val coinListLocalSource: CoinListLocalSource,
        private val coinlistremotesourceImpl: CoinListRemoteSource_impl
) : Repository {

    fun loadCoins(): Flow<Resource<List<Coin>>> =
            coinlistremotesourceImpl.fetchCoins()
                .map { extractIdsAndFetchHistory(it) }.flowOn(Dispatchers.IO)


    private suspend fun extractIdsAndFetchHistory(resource: Resource<List<Coin>>): Resource<List<Coin>> {
       val coins = when(resource) {
           is Resource.Success -> resource.data
           else -> null
       }
        coins?.map { coin ->
            coinlistremotesourceImpl.fetchCoinHistory(coin.id)
                .collect {
                    when(it) {
                        is Resource.Success -> it.data?.let { history -> coin.copy( history = history) }
                    }
                }
        }

        return Resource.Success(coins)
    }



    /*private fun fetchCoinHistory(id: Int): Flow<Resource<CoinHistory24H>> {
        return object : NetworkBoundResource<CoinHistory24H, Result>() {
            override suspend fun saveCallResult(item: Result) {
                val coinHistory24H = CoinHistory24H(item.data.coinHistory, id)
                coinListDao.insertCoinHistory(coinHistory24H)
            }

            override fun loadFromDb(): Flow<CoinHistory24H> {
                return coinListDao.loadCoinHistory24H(id)
            }

            override fun shouldLoadDb(): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<Resource<Result>> {
                return coinService.getCoinHistory24h(id)
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun fetchCoinsWithHistory() = flow<Resource<List<Coin>>> {

        emit(Resource.loading())

        val coins = loadCoins().first().data
        coins?.map {
            val history24H = fetchCoinHistory(it.id).first().data!!
            it.history = history24H
            it
        }
        emit(Resource.success(coins))
    }*/
}
