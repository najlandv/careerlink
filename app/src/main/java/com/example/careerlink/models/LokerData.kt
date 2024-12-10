package com.example.careerlink.models

data class LokerData(
    val id_loker: Int,
    val tanggal_posting: String,
    val perusahaan: String,
    val judul_loker: String,
    val alamat: String,
    val posisi_loker: String,
    val kualifikasi: String,
    val jenis_loker: String,
    val deskripsi_loker: String,
    val kontak: String,
    val gambar_loker: String?,
    val createdAt: String,
    val updatedAt: String,
    val id_pengguna: Int,
    val Pengguna: PenggunaData
)
