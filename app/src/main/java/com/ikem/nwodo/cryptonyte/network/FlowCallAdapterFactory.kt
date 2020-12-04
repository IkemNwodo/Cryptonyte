package com.ikem.nwodo.cryptonyte.network

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory : Factory() {

    companion object {
        fun create() = FlowCallAdapterFactory()
    }

    override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) !=  Flow::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        if (rawObservableType != Resource::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        val bodyType = getParameterUpperBound(0, observableType)
        return FlowCallAdapter<Any>(bodyType)
    }
}
