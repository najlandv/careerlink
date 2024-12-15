package com.example.careerlink.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    val email: String,

    val password: String,

    val confirmPassword: String,

    @SerializedName("nama_lengkap")
    val namaLengkap: String,

    @SerializedName("nama_pengguna")
    val namaPengguna: String
)
