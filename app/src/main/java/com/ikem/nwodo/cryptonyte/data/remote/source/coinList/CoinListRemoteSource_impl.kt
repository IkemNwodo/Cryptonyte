package com.ikem.nwodo.cryptonyte.data.remote.source.coinList

import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.data.local.db.model.History
import com.ikem.nwodo.cryptonyte.data.local.db.model.Result
import com.ikem.nwodo.cryptonyte.data.remote.network.api.CoinService
import com.ikem.nwodo.cryptonyte.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinListRemoteSource_impl @Inject constructor(val coinService: CoinService) {
    fun fetchCoins(): Flow<Resource<List<Coin>>> = flow {

        val result = coinService.cryptoCurrencies()
        if (result.isSuccessful){
            emit(Resource.Success(result.body()?.data?.coins))
        }else {
            emit(Resource.Error(result.errorBody().toString()))
        }
    }

    fun fetchCoinHistory(id: Int): Flow<Resource<List<History>>> = flow{
        val result = coinService.getCoinHistory24h(id)
        if (result.isSuccessful){
            emit(Resource.Success(result.body()?.data?.coinHistory))
        }else {
            emit(Resource.Error(result.errorBody().toString()))
        }
    }
}