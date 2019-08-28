package com.ikem.nwodo.cryptonyte.repository

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.db.Coin
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.model.Result
import com.ikem.nwodo.cryptonyte.network.api.ApiResponse
import com.ikem.nwodo.cryptonyte.utils.AppExecutors
import com.ikem.nwodo.cryptonyte.utils.NetworkBoundResource
import com.ikem.nwodo.cryptonyte.utils.Resource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinListRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val coinListDao: CoinDao,
        private val coinService: CoinService
) {

    fun loadCoins() : LiveData<Resource<List<Coin>>> {
        return object : NetworkBoundResource<List<Coin>, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                coinListDao.insertCoins(item.data.coins)
            }

            override fun shouldFetch(data: List<Coin>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Coin>> {
                return coinListDao.loadCoins()
            }

            override fun createCall(): LiveData<ApiResponse<Result>> {
                return coinService.cryptoCurrencies()
            }

        }.asLiveData()
    }

}