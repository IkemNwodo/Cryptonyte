package com.ikem.nwodo.cryptonyte.main;

import android.content.Context;

import com.ikem.nwodo.cryptonyte.CryptoAdapter;
import com.ikem.nwodo.cryptonyte.di.scope.MainActivityScope;

import dagger.Module;
import dagger.Provides;

//@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

}
