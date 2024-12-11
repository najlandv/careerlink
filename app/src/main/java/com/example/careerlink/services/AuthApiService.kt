package com.example.careerlink.services

import com.example.careerlink.models.LoginRequest
import com.example.careerlink.models.LoginResponse
import com.example.careerlink.models.RegisterRequest
import com.example.careerlink.models.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

}