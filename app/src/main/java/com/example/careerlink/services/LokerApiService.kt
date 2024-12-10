package com.example.careerlink.services

import com.example.careerlink.models.LokerData
import com.example.careerlink.models.LokerResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface LokerApiService {
    @GET("api/loker")
    suspend fun getLokerList(@Header("Authorization") token: String): LokerResponse

    @GET("api/loker/myposts")
    suspend fun getMyPostLokerList(@Header("Authorization") token: String): LokerResponse

    @Multipart
    @POST("api/lokerupload")
    suspend fun postLoker(
        @Header("Authorization") token: String,
        @Part("perusahaan") perusahaan: RequestBody,
        @Part("judul_loker") judulLoker: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("posisi_loker") posisiLoker: RequestBody,
        @Part("kualifikasi") kualifikasi: RequestBody,
        @Part("jenis_loker") jenisLoker: RequestBody,
        @Part("deskripsi_loker") deskripsiLoker: RequestBody,
        @Part("kontak") kontak: RequestBody,
        @Part image: MultipartBody.Part? = null
    ) : Response<Unit>

    @DELETE("api/lokerupload/{id}")
    suspend fun deleteLoker(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : Response<Unit>

    @GET("api/loker/{id}")
    suspend fun getLokerById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : Response<LokerData>

}