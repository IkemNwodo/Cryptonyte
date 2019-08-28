package com.ikem.nwodo.cryptonyte.network.api

import androidx.lifecycle.LiveData
import com.ikem.nwodo.cryptonyte.model.Data
import com.ikem.nwodo.cryptonyte.model.Result
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET("v1/public/coins")
    fun cryptoCurrencies(): LiveData<ApiResponse<Result>>

    @GET("v1/public/coin/{id}/history/24h")
    fun getCoinHistory24h(@Path("id") id: Int): Call<Data>
}
