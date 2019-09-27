package com.ikem.nwodo.cryptonyte

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.ikem.nwodo.cryptonyte.di.DaggerCoinComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class CoinApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoinComponent.builder().application(this).build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}