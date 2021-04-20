package com.ikem.data.remote.api

import com.ikem.data.remote.response.CoinApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v1/public/coins")
    suspend fun cryptoCurrencies(): Response<CoinApiResponse>

    @GET("v1/public/coin/{id}/history/24h")
    suspend fun getCoinHistory24h(@Path("id") id: Int): Response<CoinApiResponse>

    @GET("v1/public/coin/{id}/history/7d")
    suspend fun getCoinHistory7d(@Path("id") id: Int): Response<CoinApiResponse>

    @GET("v1/public/coin/{id}/history/30d")
    suspend fun getCoinHistory30d(@Path("id") id: Int): Response<CoinApiResponse>

    @GET("v1/public/coin/{id}/history/1y")
    suspend fun getCoinHistory1y(@Path("id") id: Int): Response<CoinApiResponse>
}