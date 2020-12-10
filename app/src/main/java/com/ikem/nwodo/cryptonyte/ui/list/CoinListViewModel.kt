package com.ikem.nwodo.cryptonyte.ui.list

import androidx.lifecycle.*
import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.repository.CoinListRepository
import com.ikem.nwodo.cryptonyte.utils.Resource
import javax.inject.Inject

class CoinListViewModel @Inject constructor(private val coinListRepository: CoinListRepository) : ViewModel() {

    val coins: LiveData<Resource<List<Coin>>> = coinListRepository
            .loadCoins()
        .asLiveData(viewModelScope.coroutineContext)

    /*val coinHistory: LiveData<Resource<List<Coin>>>
        get() = coins
*/
    fun refresh() = coins
}
