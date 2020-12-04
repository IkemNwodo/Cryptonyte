package com.ikem.nwodo.cryptonyte.ui.list

import androidx.lifecycle.*
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.CoinsWithHistory
import com.ikem.nwodo.cryptonyte.repository.CoinListRepository
import com.ikem.nwodo.cryptonyte.utils.Resource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CoinListViewModel @Inject constructor(private val coinListRepository: CoinListRepository) : ViewModel() {

    init {

    }
    private val coins = coinListRepository
            .fetchCoinsWithHistory()
        .asLiveData(viewModelScope.coroutineContext)

    val coinHistory: LiveData<Resource<List<Coin>>>
        get() = coins

    fun refresh() = coinHistory
}
