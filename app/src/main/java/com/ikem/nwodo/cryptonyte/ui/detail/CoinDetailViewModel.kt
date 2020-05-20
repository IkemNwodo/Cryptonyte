package com.ikem.nwodo.cryptonyte.ui.detail

import androidx.lifecycle.*
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.CoinHistory24H
import com.ikem.nwodo.cryptonyte.db.model.Data
import com.ikem.nwodo.cryptonyte.repository.CoinDetailRepository
import com.ikem.nwodo.cryptonyte.ui.list.RateLimiter
import com.ikem.nwodo.cryptonyte.utils.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(val repository: CoinDetailRepository) : ViewModel() {

    private val _coinId: MutableLiveData<Int> = MutableLiveData()


    val coinHistory24h: LiveData<Resource<CoinHistory24H>> = Transformations.switchMap(_coinId) { id ->
        repository
            .fetchCoinHistory(id)
            .asLiveData(viewModelScope.coroutineContext)
    }

    /*val coinHistory7d: LiveData<Resource<Data>> = Transformations.switchMap(_coinId) { id ->
        repository.fetchCoinHistory7d(id)
    }

    val coinHistory30d: LiveData<Resource<Data>> = Transformations.switchMap(_coinId) { id ->
        repository.fetchCoinHistory30d(id)
    }

    val coinHistory1y: LiveData<Resource<Data>> = Transformations.switchMap(_coinId) { id ->
        repository.fetchCoinHistory1y(id)
    }
*/
    val singleCoin: LiveData<Resource<Coin>> =
        Transformations.switchMap(_coinId) { id ->
            repository
                .loadSingleCoin(id)
                .asLiveData(viewModelScope.coroutineContext)
        }

    val coins: LiveData<Resource<List<Coin>>>
        get() = repository
            .loadCoins()
            .asLiveData(viewModelScope.coroutineContext)

    fun setId(id: Int) {
        _coinId.value = id
    }
}

