package com.example.cryptowatch.repositories

import com.example.cryptowatch.data.network.RetrofitInstance

class MainRepository {
    suspend fun fetchCoinMarketData()=RetrofitInstance.api.fetchCoinMarketData()
    suspend fun fetchCoinPriceHistory()=RetrofitInstance.api.fetchCoinPriceHistory()
}