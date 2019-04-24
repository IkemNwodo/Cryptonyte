package com.ikem.nwodo.cryptonyte.di;

import com.ikem.nwodo.cryptonyte.CryptoService;
import com.ikem.nwodo.cryptonyte.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = NetworkModule.class)
public interface CoinComponent {

    CryptoService getCryptoService();

}
