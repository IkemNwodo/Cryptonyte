package com.ikem.nwodo.cryptonyte.di

import com.ikem.nwodo.cryptonyte.ui.detail.CoinDetailFragment
import com.ikem.home.ui.CoinListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCoinListFragment(): com.ikem.home.ui.CoinListFragment

    @ContributesAndroidInjector
    abstract fun contributeCoinDetailFragment(): CoinDetailFragment
}