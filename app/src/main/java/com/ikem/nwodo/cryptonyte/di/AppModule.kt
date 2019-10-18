package com.ikem.nwodo.cryptonyte.di

import android.app.Application
import android.content.Context

import androidx.room.Room

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ikem.nwodo.cryptonyte.db.CoinDao
import com.ikem.nwodo.cryptonyte.db.CoinDatabase
import com.ikem.nwodo.cryptonyte.network.LiveDataCallAdapterFactory
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.utils.Constants

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ikem.nwodo.cryptonyte.network.RequestInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class AppModule {


    @Singleton
    @Provides
    fun provideContext(application: Application): Context{
        return application
    }

    @Singleton
    @Provides
    fun provideCryptoService(retrofit: Retrofit): CoinService {
        return retrofit.create(CoinService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build()

    }

    @Singleton
    @Provides
    fun provideOkHttpclient(): OkHttpClient{
        val requestInterceptor = RequestInterceptor()
        return OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun providesCoinDatabase(application: Application): CoinDatabase {
        return Room
                .databaseBuilder(application, CoinDatabase::class.java, "coin_database")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun providesCoinDao(coinDatabase: CoinDatabase): CoinDao {
        return coinDatabase.coinDao()
    }
    /**
     * @Provides
     * public GsonConverterFactory gsonConverterFactory(Gson gson){
     * return GsonConverterFactory.create(gson);
     * }
     */
}
