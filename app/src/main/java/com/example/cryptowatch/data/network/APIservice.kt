package com.example.cryptowatch.data.network

import com.example.cryptowatch.models.CoinHistory
import com.example.cryptowatch.models.CoinItem
import com.example.cryptowatch.models.Coins
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("coins/markets")
    suspend fun fetchCoinMarketData(
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("order")order:String="market_cap_desc",
        @Query("per_page")perPage : Int=50,
        @Query("page")page:Int=1
        ):Response<List<CoinItem>>

    @GET("coins/{id}/market_chart")
    suspend fun fetchCoinPriceHistory(
        @Path("id")id:String="bitcoin",
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "10",
        @Query("interval") interval: String = "daily",
    ):Response<CoinHistory>
}
