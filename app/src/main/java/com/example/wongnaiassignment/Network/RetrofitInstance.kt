package com.example.wongnaiassignment.Network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    const val BASE_URL = "https://api.coinranking.com/v1/public/"
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RetroService by lazy {
        retrofit.create(RetroService::class.java)
    }
}
