package com.example.wongnaiassignment.Model

data class Response(
    val status: String,
    val data: CoinList,
)

data class CoinList(
    val coins: List<Coin>,
)

data class Coin(
    val id: Int,
    val name: String?,
    val symbol: String?,
    val slug: String?,
    val description: String?,
    val iconUrl: String?,
)
