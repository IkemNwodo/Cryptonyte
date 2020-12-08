package com.ikem.nwodo.cryptonyte.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<DB, REMOTE> {

    fun asFlow() = flow<Resource<DB>>{
        emit(Resource.Loading)

        //val localData = loadFromDb().first()

        // checking if local data is staled
        if (shouldLoadDb()){
            // need remote data
            createCall().collect{ response ->
                when (response) {

                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        response.data?.let { saveCallResult(it) }
                    }

                    is Resource.Error -> {
                        emit(Resource.Error(response.toString()))
                    }
                }
            }
        }
    }


    @WorkerThread
    protected abstract suspend fun saveCallResult(item: REMOTE)

    @MainThread
    protected abstract fun loadFromDb(): Flow<DB>

    @MainThread
    protected abstract fun shouldLoadDb(): Boolean

    @MainThread
    protected abstract suspend fun createCall(): Flow<Resource<REMOTE>>


}
