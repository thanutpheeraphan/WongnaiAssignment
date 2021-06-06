package com.example.wongnaiassignment.Model

data class ResponseData(
    val status: String,
    val data: DataS,
)

data class DataS(
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
