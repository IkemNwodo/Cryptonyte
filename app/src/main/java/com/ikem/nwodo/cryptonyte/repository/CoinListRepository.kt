package com.ikem.nwodo.cryptonyte.repository

import android.util.Log
import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.data.local.source.coinList.CoinListLocalSource
import com.ikem.nwodo.cryptonyte.data.remote.source.coinList.CoinListRemoteSource_impl
import com.ikem.nwodo.cryptonyte.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinListRepository @Inject constructor(
        private val coinlistremotesourceImpl: CoinListRemoteSource_impl
) : Repository {

    fun loadCoins(): Flow<Resource<List<Coin>>> =
            coinlistremotesourceImpl.fetchCoins()
                    .map { extractIdsAndFetchHistory(it) }.flowOn(Dispatchers.IO)


    private suspend fun extractIdsAndFetchHistory(resource: Resource<List<Coin>>): Resource<List<Coin>> = withContext(Dispatchers.IO){
        val coins = when (resource) {
            is Resource.Success -> resource.data
            else -> null
        }
        coins?.map {
            it.histories = when (val history = coinlistremotesourceImpl.fetchCoinHistory(it.id).first()) {
                is Resource.Success -> {
                    history.data
                }
                else -> null
            }
        }

        return@withContext Resource.Success(coins)
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
