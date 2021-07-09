package com.example.cryptowatch.models

data class Coins(
    val coins: List<CoinItem>
) : ArrayList<CoinItem>()