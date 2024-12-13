package com.example.careerlink.services

import com.example.careerlink.models.SertifikasiResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
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

}