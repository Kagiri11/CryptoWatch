package com.example.cryptowatch.models

data class CoinHistory(
    val market_caps: List<List<Double>>,
    val prices: List<List<Double>>,
    val total_volumes: List<List<Double>>
)