package com.example.careerlink.models

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(

    @SerializedName("password_lama")
    val passwordLama: String,

    @SerializedName("password_baru")
    val passwordBaru: String,

    @SerializedName("konfirmasi_password")
    val konfirmasiPassword: String

)
