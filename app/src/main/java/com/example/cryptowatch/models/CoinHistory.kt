package com.example.cryptowatch.models

data class CoinHistory(
    val market_caps: List<List<Long>>,
    val prices: List<List<Long>>,
    val total_volumes: List<List<Long>>
)