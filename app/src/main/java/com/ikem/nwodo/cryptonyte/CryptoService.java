package com.ikem.nwodo.cryptonyte;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CryptoService {

    @GET("v1/public/coins")
    Single<Result> getCryptoCurrencies();

    @GET("v1/public/coin/{id}/history/24h")
    Single<Data> getCoinHistory24h(@Path("id") int id);
}
