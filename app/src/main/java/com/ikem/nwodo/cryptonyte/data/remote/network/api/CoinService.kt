package com.ikem.nwodo.cryptonyte.data.remote.network.api

import com.ikem.nwodo.cryptonyte.data.local.db.model.Result
import com.ikem.nwodo.cryptonyte.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET("v1/public/coins")
    fun cryptoCurrencies(): Response<Result>

    @GET("v1/public/coin/{id}/history/24h")
    fun getCoinHistory24h(@Path("id") id: Int): Response<Result>

    @GET("v1/public/coin/{id}/history/7d")
    fun getCoinHistory7d(@Path("id") id: Int): Response<Result>

    @GET("v1/public/coin/{id}/history/30d")
    fun getCoinHistory30d(@Path("id") id: Int): Response<Result>

    @GET("v1/public/coin/{id}/history/1y")
    fun getCoinHistory1y(@Path("id") id: Int): Response<Result>
}