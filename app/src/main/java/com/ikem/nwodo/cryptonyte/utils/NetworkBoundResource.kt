package com.ikem.nwodo.cryptonyte.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<DB, REMOTE> {

    fun asFlow() = flow<Resource<DB>>{
        emit(Resource.loading())

        val localData = loadFromDb().first()

        // checking if local data is staled
        if (shouldFetchFromRemote(localData)){
            // need remote data
            createCall().collect{ response ->
                when (response.status) {

                    Status.LOADING -> {
                        //emit(Resource.loading())
                    }

                    Status.SUCCESS -> {
                        val data = response.data
                        if (data != null) {
                            saveCallResult(data)
                        }

                        // load the data immediately
                        emitLocalDbData()
                    }

                    Status.ERROR -> {
                        emit(Resource.error(response.message))
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    private suspend fun FlowCollector<Resource<DB>>.emitLocalDbData() {
        //emit(Resource.loading())

        emitAll(loadFromDb().map { dbData ->
            Resource.success(dbData)
        })
    }


    @WorkerThread
    protected abstract suspend fun saveCallResult(item: REMOTE)

    @MainThread
    protected abstract fun loadFromDb(): Flow<DB>

    @MainThread
    protected abstract fun shouldFetchFromRemote(item: DB): Boolean

    @MainThread
    protected abstract suspend fun createCall(): Flow<Resource<REMOTE>>
}
