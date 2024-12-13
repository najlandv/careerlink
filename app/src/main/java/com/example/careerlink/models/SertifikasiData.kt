package com.example.careerlink.models

import com.google.gson.annotations.SerializedName

data class SertifikasiData(

    @SerializedName("id_sertifikasi")
    val idSertifikasi: Int,

    @SerializedName("tanggal_posting")
    val tanggalPosting: String,

    val instansi: String,

    @SerializedName("judul_sertifikasi")
    val judulSertifikasi: String,

    val deskripsi: String,

    @SerializedName("tanggal_pelaksanaan")
    val tanggalPelaksanaan: String,

    @SerializedName("gambar_sertifikasi")
    val gambarSertifikasi: String,

    val kontak: String,

    val harga: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("id_pengguna")
    val idPengguna: Int,

    val Pengguna: PenggunaData

)
