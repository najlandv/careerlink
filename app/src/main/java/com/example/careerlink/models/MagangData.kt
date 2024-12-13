package com.example.careerlink.models

import com.google.gson.annotations.SerializedName

data class MagangData(
    @SerializedName("id_magang")
    val idMagang: Int,

    @SerializedName("tanggal_posting")
    val tanggalPosting: String,

    val perusahaan: String,

    @SerializedName("judul_magang")
    val judulMagang: String,

    val alamat: String,

    @SerializedName("posisi_magang")
    val posisiMagang: String,

    val kualifikasi: String,

    @SerializedName("jenis_magang")
    val jenisMagang: String,

    @SerializedName("durasi_magang")
    val durasiMagang: String,

    @SerializedName("deskripsi_magang")
    val deskripsiMagang: String,

    val kontak: String,

    @SerializedName("gambar_magang")
    val gambarMagang: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("id_pengguna")
    val idPengguna: Int,

    val Pengguna: PenggunaData
)
