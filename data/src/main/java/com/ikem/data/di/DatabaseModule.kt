package com.ikem.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ikem.data.local.room.CoinDao
import com.ikem.data.local.room.CoinDatabase
import com.ikem.data.local.room.CoinDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesCoinDatabase(@ApplicationContext context: Context): CoinDatabase {
        return Room
                .databaseBuilder(context, CoinDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun providesCoinDao(coinDatabase: CoinDatabase): CoinDao {
        return coinDatabase.coinDao()
    }
}