package com.ikem.nwodo.cryptonyte.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ikem.nwodo.cryptonyte.ui.detail.CoinDetailViewModel
import com.ikem.nwodo.cryptonyte.ui.list.CoinListViewModel
import com.ikem.nwodo.cryptonyte.viewmodel.CoinViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CoinListViewModel::class)
    abstract fun bindCoinListViewModel(coinListViewModel: CoinListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailViewModel::class)
    abstract fun bindCoinDetailViewModel(coinDetailViewModel: CoinDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CoinViewModelFactory): ViewModelProvider.Factory
}