package com.ikem.nwodo.cryptonyte.ui.list

import androidx.lifecycle.ViewModel;
import com.ikem.nwodo.cryptonyte.repository.CoinListRepository
import javax.inject.Inject

class CoinListViewModel @Inject constructor(private val coinListRepository: CoinListRepository): ViewModel() {

    fun loadCoins() = coinListRepository.loadCoins()
}
