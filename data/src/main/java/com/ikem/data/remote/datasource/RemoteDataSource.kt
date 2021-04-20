package com.ikem.data.remote.datasource

import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getCryptos(): Flow<List<>>
}