package com.ikem.nwodo.cryptonyte.repository

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.db.model.Data
import com.ikem.nwodo.cryptonyte.db.model.History
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.db.model.Result
import com.ikem.nwodo.cryptonyte.utils.AppExecutors
import com.ikem.nwodo.cryptonyte.utils.NetworkBoundResource
import com.ikem.nwodo.cryptonyte.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinDetailRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val coinDao: CoinDao,
        private val coinService: CoinService
) {
    fun fetchCoinHistory(id: Int) : LiveData<Resource<Data>>{
        return object : NetworkBoundResource<Data, Result>(appExecutors) {
            override fun saveCallResult(item: Result) {
                coinDao.insertCoinHistory(item.data)
            }

            override fun shouldFetch(data: Data?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Data> {
               return coinDao.loadCoinHistory(id)
            }

            override fun createCall(): LiveData<Resource<Result>> {
                return coinService.getCoinHistory24h(id)
            }

        }.asLiveData()
    }
}