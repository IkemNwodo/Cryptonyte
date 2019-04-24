package com.ikem.nwodo.cryptonyte.main;

import com.ikem.nwodo.cryptonyte.CryptoAdapter;
import com.ikem.nwodo.cryptonyte.CryptoService;
import com.ikem.nwodo.cryptonyte.di.CoinComponent;
import com.ikem.nwodo.cryptonyte.di.scope.MainActivityScope;

import dagger.Component;

//@Component(dependencies = CoinComponent.class)
//@MainActivityScope
public interface MainActivityComponent {

    CryptoAdapter getCryptoAdapter();

    CryptoService getCryptoService();
}
