package com.ikem.nwodo.cryptonyte.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
                .build()

        val request = originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}