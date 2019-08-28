package com.ikem.nwodo.cryptonyte.repository

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.db.Coin
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.model.Result
import com.ikem.nwodo.cryptonyte.network.api.ApiResponse
import com.ikem.nwodo.cryptonyte.utils.AppExecutors
import com.ikem.nwodo.cryptonyte.utils.NetworkBoundResource
import com.ikem.nwodo.cryptonyte.utils.Resource
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinDetailRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val coinDao: CoinDao,
        private val coinService: CoinService
) {
    fun loadCoin(id: Int) : LiveData<Resource<List<Coin>>>{
        return object : NetworkBoundResource<List<Coin>, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                coinDao.insertCoins(item.data.coins)
            }

            override fun shouldFetch(data: List<Coin>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Coin>> {
               return coinDao.loadCoins()
            }

            override fun createCall(): LiveData<ApiResponse<Result>> {
                return coinService.cryptoCurrencies()
            }

        }.asLiveData()
    }
}