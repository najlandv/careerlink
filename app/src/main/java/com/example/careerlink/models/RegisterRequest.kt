package com.example.careerlink.models

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val fullName: String,
    val username: String
)
