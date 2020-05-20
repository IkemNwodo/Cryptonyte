package com.ikem.nwodo.cryptonyte.network

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.*
import ru.gildor.coroutines.retrofit.awaitResponse
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class FlowCallAdapter<R>(private val responseType: Type) :
        CallAdapter<R, Flow<Resource<R>>> {

    override fun responseType(): Type {
        return responseType
    }

    @ExperimentalCoroutinesApi
    override fun adapt(call: Call<R>): Flow<Resource<R>> = flow {
        emit(Resource.loading())

        val response = call.awaitResponse()
        if (response.isSuccessful){
            emit(Resource.success(response.body()) as Resource<R>)
        }else {
            emit(Resource.error<R>(response.message()))
        }
    }.catch { emit(Resource.error(it.message)) }
}

private fun <T> Response<T>.toResource(): Resource<T> {
    val error = errorBody()?.toString() ?: message()
    return when {
        isSuccessful -> {
            val body = body()
            when {
                body != null -> Resource.success(body)
                else -> Resource.error(error)
            }
        }
        else -> Resource.error(error)
    }
}