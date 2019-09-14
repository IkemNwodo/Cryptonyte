package com.ikem.nwodo.cryptonyte.utils

import com.ikem.nwodo.cryptonyte.utils.Status.*
/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<T>(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String?): Resource<T> {
            return Resource(ERROR, message = msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}
