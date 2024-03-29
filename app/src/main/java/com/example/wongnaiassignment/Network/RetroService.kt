package com.example.wongnaiassignment.Network

import com.example.wongnaiassignment.Model.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetroService {

    @GET("coins")
    suspend fun getDataWithParams(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("prefix") prefix: String?,
        @Query("sort") sort: String?,
    ): Response

}