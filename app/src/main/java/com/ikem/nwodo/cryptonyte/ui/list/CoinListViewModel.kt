package com.ikem.nwodo.cryptonyte.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel;
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.repository.CoinListRepository
import com.ikem.nwodo.cryptonyte.utils.Resource
import javax.inject.Inject

class CoinListViewModel @Inject constructor(private val coinListRepository: CoinListRepository): ViewModel() {

    private val _value = MutableLiveData<String>()

    fun reFetch(input: String){
        _value.value =  input
    }
    val loadCoins: LiveData<Resource<List<Coin>>> = Transformations.switchMap(_value){ coinListRepository.loadCoins()}

    fun loadCoinHistory(id: Int) = coinListRepository.fetchCoinHistory(id)
}
