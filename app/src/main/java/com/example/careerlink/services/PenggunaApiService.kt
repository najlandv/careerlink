package com.example.careerlink.services

import com.example.careerlink.models.ChangePasswordRequest
import com.example.careerlink.models.PenggunaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface PenggunaApiService{

    @GET("api/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ) : PenggunaResponse

    @PATCH("/api/ubah-password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body request: ChangePasswordRequest
    ) : Response<Unit>
}