package com.ikem.nwodo.cryptonyte.di

import android.app.Application
import android.content.Context

import androidx.room.Room

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ikem.nwodo.cryptonyte.data.local.db.CoinDao
import com.ikem.nwodo.cryptonyte.data.local.db.CoinDatabase
import com.ikem.nwodo.cryptonyte.data.remote.network.api.CoinService
import com.ikem.nwodo.cryptonyte.utils.Constants

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

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
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpclient(): OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
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
