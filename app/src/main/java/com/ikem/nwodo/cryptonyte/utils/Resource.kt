package com.ikem.nwodo.cryptonyte.utils

import com.ikem.nwodo.cryptonyte.utils.Status.*
/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */

sealed class Resource<out T>{
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Error(val exception: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}
