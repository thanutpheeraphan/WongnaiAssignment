package com.example.wongnaiassignment.Repository

import com.example.wongnaiassignment.Model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetroService {

    @GET("coins")
    suspend fun getDataWithLimit(@Query("limit") limit: Int): ResponseData

}