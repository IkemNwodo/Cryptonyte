package com.ikem.nwodo.cryptonyte.ui.list

import androidx.lifecycle.*
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.CoinsWithHistory
import com.ikem.nwodo.cryptonyte.repository.CoinListRepository
import com.ikem.nwodo.cryptonyte.utils.Resource
import javax.inject.Inject

class CoinListViewModel @Inject constructor(private val coinListRepository: CoinListRepository) : ViewModel() {

    val coinHistory = coinListRepository
            .fetchCoinsWithHistory
            .asLiveData(viewModelScope.coroutineContext)

    fun refresh() = coinHistory
}
