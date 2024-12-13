package com.example.careerlink.services

import com.example.careerlink.models.DetailMagangResponse
import com.example.careerlink.models.MagangResponse
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

interface MagangApiService {

    @GET("api/magang")
    suspend fun getMagangList(@Header("Authorization") token: String) : MagangResponse

    @GET("api/magang/myposts")
    suspend fun getMyPostMagangList(@Header("Authorization") token: String) : MagangResponse

    @DELETE("api/magangupload/{id}")
    suspend fun deleteMagang(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : Response<Unit>

    @Multipart
    @POST("api/magangupload")
    suspend fun postMagang(
        @Header("Authorization") token: String,
        @Part("perusahaan") perusahaan: RequestBody,
        @Part("judul_magang") judulMagang: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("posisi_magang") posisiMagang: RequestBody,
        @Part("kualifikasi") kualifikasi: RequestBody,
        @Part("jenis_magang") jenisMagang: RequestBody,
        @Part("durasi_magang") durasiMagang: RequestBody,
        @Part("deskripsi_magang") deskripsiMagang: RequestBody,
        @Part("kontak") kontak: RequestBody,
        @Part image: MultipartBody.Part? = null
    ) : Response<Unit>

    @GET("api/magang/{id}")
    suspend fun getMagangById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : Response<DetailMagangResponse>

    @Multipart
    @PATCH("api/magangupload/{id}")
    suspend fun updateMagang(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Part("perusahaan") perusahaan: RequestBody,
        @Part("judul_magang") judulMagang: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("posisi_magang") posisiMagang: RequestBody,
        @Part("kualifikasi") kualifikasi: RequestBody,
        @Part("jenis_magang") jenisMagang: RequestBody,
        @Part("durasi_magang") durasiMagang: RequestBody,
        @Part("deskripsi_magang") deskripsiMagang: RequestBody,
        @Part("kontak") kontak: RequestBody,
        @Part image: MultipartBody.Part? = null
    ) : Response<Unit>
}