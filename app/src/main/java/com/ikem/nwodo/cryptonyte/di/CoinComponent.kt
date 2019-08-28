package com.ikem.nwodo.cryptonyte.di

import android.app.Application
import com.ikem.nwodo.cryptonyte.CoinApplication

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    MainActivityModule::class,
    ViewModelModule::class])
internal interface CoinComponent : AndroidInjector<CoinApplication> {

    @Component.Builder
     interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoinComponent
    }
}