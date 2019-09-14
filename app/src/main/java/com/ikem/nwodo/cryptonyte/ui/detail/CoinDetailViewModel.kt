package com.ikem.nwodo.cryptonyte.ui.detail

import androidx.lifecycle.ViewModel
import com.ikem.nwodo.cryptonyte.repository.CoinDetailRepository
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(val repository: CoinDetailRepository): ViewModel() {

    fun loadCoinHistory(id: Int)= repository.fetchCoinHistory(id)
}
