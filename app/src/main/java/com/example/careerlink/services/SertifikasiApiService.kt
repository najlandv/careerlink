package com.example.careerlink.services

import com.example.careerlink.models.DetailSertifikasiResponse
import com.example.careerlink.models.SertifikasiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface SertifikasiApiService {

    @GET("api/sertifikasi")
    suspend fun getSertifikasiList(
        @Header("Authorization") token: String
    ) : SertifikasiResponse

    @GET("api/sertifikasi/myposts")
    suspend fun getMyPostSertfikasiList(
        @Header("Authorization") token: String
    ) : SertifikasiResponse

    @DELETE("api/sertifikasiupload/{id}")
    suspend fun deleteSertifikasi(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : Response<Unit>

    @Multipart
    @POST("api/sertifikasiupload")
    suspend fun postSertifikasi(
        @Header("Authorization") token: String,
        @Part("instansi") instansi: RequestBody,
        @Part("judul_sertifikasi") judulSertifiksai: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("tanggal_pelaksanaan") tanggalPelaksanaan: RequestBody,
        @Part("kontak") kontak: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part? = null
    ) : Response<Unit>

    @GET("api/sertifikasi/{id}")
    suspend fun getSertifikasiById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : Response<DetailSertifikasiResponse>

    @PATCH("api/sertifikasiupload/{id}")
    suspend fun updateSertifikasi(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Part("instansi") instansi: RequestBody,
        @Part("judul_sertifikasi") judulSertifiksai: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("tanggal_pelaksanaan") tanggalPelaksanaan: RequestBody,
        @Part("kontak") kontak: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part? = null
    ) : Response<Unit>

}