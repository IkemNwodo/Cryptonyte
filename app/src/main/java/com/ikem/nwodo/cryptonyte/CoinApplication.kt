package com.ikem.nwodo.cryptonyte

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.ikem.nwodo.cryptonyte.di.DaggerCoinComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class CoinApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this);
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoinComponent.builder().application(this).build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}