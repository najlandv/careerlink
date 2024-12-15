package com.example.careerlink.models

import com.google.gson.annotations.SerializedName

data class PenggunaData(


    @SerializedName("id_pengguna")
    val idPengguna: Int,

    @SerializedName("nama_lengkap")
    val namaLengkap: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("nama_pengguna")
    val namaPengguna: String

)
