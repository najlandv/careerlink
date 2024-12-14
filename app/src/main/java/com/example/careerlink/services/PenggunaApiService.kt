package com.example.careerlink.services

import com.example.careerlink.models.PenggunaResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface PenggunaApiService{

    @GET("api/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ) : PenggunaResponse

}