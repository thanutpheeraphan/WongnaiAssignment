package com.example.wongnaiassignment.Model

data class ResponseData(
    val status: String,
    val coins: List<Coins>,
)


data class Coins(
    val id: Int?,
    val name: String?,
    val symbol: String?,
    val slug: String?,
    val description: String?,
    val iconUrl: String?,
)