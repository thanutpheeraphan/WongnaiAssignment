package com.example.wongnaiassignment.Network

import com.example.wongnaiassignment.Model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query


interface RetroService {

    @GET("coins")
    suspend fun getDataWithLimit(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): ResponseData

}