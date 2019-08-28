package com.ikem.nwodo.cryptonyte.di;

import com.ikem.nwodo.cryptonyte.MainActivity
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(
            modules = [FragmentBuildersModule::class]
    )
    abstract fun contributeMainActivity() : MainActivity
}
