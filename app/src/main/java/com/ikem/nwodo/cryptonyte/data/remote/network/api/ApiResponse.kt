package com.ikem.nwodo.cryptonyte.data.remote.network.api

import retrofit2.Response
import java.io.IOException

class ApiResponse<T> {

    val code: Int
    val body: T?
    val error: Throwable?

    val isSuccessful: Boolean
        get() = code >= 200 && code < 300


    constructor(error: Throwable?) {
        code = 500
        body = null
        this.error = error
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            error = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (ignored: IOException) {
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            error = IOException(message)
            body = null
        }
    }
}
