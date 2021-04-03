package com.ikem.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

@HiltViewModel
class CoinListViewModel @Inject constructor(private val coinListRepository: CoinListRepository) : ViewModel() {

    val coins: LiveData<Resource<List<Coin>>> = coinListRepository
            .loadCoins()
            .asLiveData(viewModelScope)

    fun refresh() = coins
}