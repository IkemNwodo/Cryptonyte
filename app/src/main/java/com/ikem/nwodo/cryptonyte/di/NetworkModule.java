package com.ikem.nwodo.cryptonyte.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ikem.nwodo.cryptonyte.Constants;
import com.ikem.nwodo.cryptonyte.CryptoService;
import com.ikem.nwodo.cryptonyte.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @ApplicationScope
    @Provides
    public CryptoService provideCryptoService(Retrofit retrofit){
        return retrofit.create(CryptoService.class);
    }

    @ApplicationScope
    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    public Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
/**
    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }*/
}
