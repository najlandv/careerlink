package com.example.careerlink.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerlink.data.TokenDataStore
import com.example.careerlink.models.LokerData
import com.example.careerlink.services.LokerApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class LokerViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val lokerApiService: LokerApiService
) : ViewModel() {

    private val _lokerList = MutableStateFlow<List<LokerData>>(emptyList())
    val lokerList: StateFlow<List<LokerData>> = _lokerList

    private val _myPostLokerList = MutableStateFlow<List<LokerData>>(emptyList())
    val myPostLokerList: StateFlow<List<LokerData>> = _myPostLokerList

    private val _lokerDetail = MutableStateFlow<LokerData?>(null)
    val lokerDetail: StateFlow<LokerData?> = _lokerDetail

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchLokerList()
    }

    private fun fetchLokerList() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = lokerApiService.getLokerList("Bearer $token")
                    _lokerList.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Gagal mengambil data loker: ${e.message}"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan."
            }
        }
    }

    fun fetchMyPostLokerList() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = lokerApiService.getMyPostLokerList("Bearer $token")
                    _myPostLokerList.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Gagal mengambil data loker: ${e.message}"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan."
            }
        }
    }

    fun postLoker(
        context: Context,
        perusahaan: String,
        judulLoker: String,
        alamat: String,
        posisiLoker: String,
        kualifikasi: String,
        jenisLoker: String,
        deskripsiLoker: String,
        kontak: String,
        imageUri: Uri? = null,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val perusahaanBody = perusahaan.toRequestBody()
                    val judulLokerBody = judulLoker.toRequestBody()
                    val alamatBody = alamat.toRequestBody()
                    val posisiLokerBody = posisiLoker.toRequestBody()
                    val kualifikasiBody = kualifikasi.toRequestBody()
                    val jenisLokerBody = jenisLoker.toRequestBody()
                    val deskripsiLokerBody = deskripsiLoker.toRequestBody()
                    val kontakBody = kontak.toRequestBody()

                    val imagePart = imageUri?.let {
                        val contentResolver = context.contentResolver
                        val type = contentResolver.getType(it) ?: "image/*"
                        val inputStream = contentResolver.openInputStream(it)
                        val bytes = inputStream?.readBytes() ?: byteArrayOf()
                        inputStream?.close()

                        val requestBody = RequestBody.create(type.toMediaTypeOrNull(), bytes)
                        MultipartBody.Part.createFormData("gambar_loker", "image.jpg", requestBody)
                    }

                    val response = lokerApiService.postLoker(
                        token = "Bearer $token",
                        perusahaan = perusahaanBody,
                        judulLoker = judulLokerBody,
                        alamat = alamatBody,
                        posisiLoker = posisiLokerBody,
                        kualifikasi = kualifikasiBody,
                        jenisLoker = jenisLokerBody,
                        deskripsiLoker = deskripsiLokerBody,
                        kontak = kontakBody,
                        image = imagePart
                    )

                    if (response.isSuccessful) {
                        onSuccess()
                    } else {
                        onError("Gagal mengirim data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError("Terjadi kesalahan: ${e.message}")
                }
            } else {
                onError("Token tidak ditemukan.")
            }
        }
    }

    private fun String.toRequestBody(): RequestBody =
        RequestBody.create("text/plain".toMediaTypeOrNull(), this)

    fun deleteLoker(
        id: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        println("deleteLoker dipanggil dengan ID: $id")
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = lokerApiService.deleteLoker("Bearer $token", id)
                    if (response.isSuccessful) {
                        fetchLokerList() // Refresh daftar loker setelah hapus
                        onSuccess()
                    } else {
                        onError("Gagal menghapus data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError("Terjadi kesalahan: ${e.message}")
                }
            } else {
                onError("Token tidak ditemukan.")
            }
        }
    }

    fun getLokerById(lokerId: Int) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = lokerApiService.getLokerById("Bearer $token", lokerId)
                    if (response.isSuccessful) {
                        val lokerResponse = response.body()
                        if (lokerResponse?.data != null) {
                            _lokerDetail.value = lokerResponse.data
                        } else {
                            _errorMessage.value = "Data loker tidak ditemukan."
                        }
                    } else {
                        _errorMessage.value = "Gagal mengambil data loker: ${response.message()}"
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Terjadi kesalahan: ${e.message}"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan."
            }
        }
    }

        fun updateLoker(
            context: Context,
            id: Int, // ID diperlukan untuk update
            perusahaan: String,
            judulLoker: String,
            alamat: String,
            posisiLoker: String,
            kualifikasi: String,
            jenisLoker: String,
            deskripsiLoker: String,
            kontak: String,
            imageUri: Uri? = null,
            onSuccess: () -> Unit,
            onError: (String) -> Unit
        ) {
            viewModelScope.launch {
                val token = tokenDataStore.accessToken.firstOrNull()
                if (!token.isNullOrEmpty()) {
                    try {
                        val perusahaanBody = perusahaan.toRequestBody()
                        val judulLokerBody = judulLoker.toRequestBody()
                        val alamatBody = alamat.toRequestBody()
                        val posisiLokerBody = posisiLoker.toRequestBody()
                        val kualifikasiBody = kualifikasi.toRequestBody()
                        val jenisLokerBody = jenisLoker.toRequestBody()
                        val deskripsiLokerBody = deskripsiLoker.toRequestBody()
                        val kontakBody = kontak.toRequestBody()

                        val imagePart = imageUri?.let {
                            val contentResolver = context.contentResolver
                            val type = contentResolver.getType(it) ?: "image/*"
                            val inputStream = contentResolver.openInputStream(it)
                            val bytes = inputStream?.readBytes() ?: byteArrayOf()
                            inputStream?.close()

                            val requestBody = RequestBody.create(type.toMediaTypeOrNull(), bytes)
                            MultipartBody.Part.createFormData("gambar_loker", "image.jpg", requestBody)
                        }

                        val response = lokerApiService.updateLoker(
                            token = "Bearer $token",
                            id = id,
                            perusahaan = perusahaanBody,
                            judulLoker = judulLokerBody,
                            alamat = alamatBody,
                            posisiLoker = posisiLokerBody,
                            kualifikasi = kualifikasiBody,
                            jenisLoker = jenisLokerBody,
                            deskripsiLoker = deskripsiLokerBody,
                            kontak = kontakBody,
                            image = imagePart
                        )

                        if (response.isSuccessful) {
                            onSuccess()
                        } else {
                            onError("Gagal memperbarui data: ${response.message()}")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        onError("Terjadi kesalahan: ${e.message}")
                    }
                } else {
                    onError("Token tidak ditemukan.")
                }
            }
        }



}