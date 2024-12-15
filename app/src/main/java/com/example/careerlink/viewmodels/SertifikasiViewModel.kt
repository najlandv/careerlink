package com.example.careerlink.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerlink.data.TokenDataStore
import com.example.careerlink.models.SertifikasiData
import com.example.careerlink.services.SertifikasiApiService
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
class SertifikasiViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val sertifikasiApiService: SertifikasiApiService
) : ViewModel() {

    private val _sertifikasiList = MutableStateFlow<List<SertifikasiData>>(emptyList())
    val sertifikasiList: StateFlow<List<SertifikasiData>> = _sertifikasiList

    private val _myPostSertifikasiList = MutableStateFlow<List<SertifikasiData>>(emptyList())
    val myPostSerifikasiList: StateFlow<List<SertifikasiData>> = _myPostSertifikasiList

    private val _sertifikasiDetail = MutableStateFlow<SertifikasiData?>(null)
    val sertifikasiDetail: StateFlow<SertifikasiData?> = _sertifikasiDetail

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchSertifikasiList()
        fetchMyPostSertifikasiList()
    }

    private fun fetchSertifikasiList() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = sertifikasiApiService.getSertifikasiList("Bearer $token")
                    _sertifikasiList.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Gagal mengambil data sertifikasi: ${e.message}"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan."
            }
        }
    }

    fun fetchMyPostSertifikasiList() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = sertifikasiApiService.getMyPostSertfikasiList("Bearer $token")
                    _myPostSertifikasiList.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Gagal mengambil data sertifikasi: ${e.message}"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan"
            }
        }
    }

    fun deleteSertifikasi(
        id: Int,
        onSucces: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = sertifikasiApiService.deleteSertifikasi("Bearer $token", id)
                    if (response.isSuccessful) {
                        fetchSertifikasiList()
                        fetchMyPostSertifikasiList()
                        onSucces()
                    } else {
                        onError("Gagal menghapus data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError("Terjadi kesalahan11: ${e.message}")
                }
            }
        }
    }

    fun postSertifikasi(
        context: Context,
        instansi: String,
        judulSertifiksai: String,
        deskripsi: String,
        tanggalPelaksanaan: String,
        kontak: String,
        harga: String,
        imageUri: Uri? = null,
        onSucces: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val instansiBody = instansi.toRequestBody()
                    val judulSertifiksaiBody = judulSertifiksai.toRequestBody()
                    val deskripsiBody = deskripsi.toRequestBody()
                    val tanggalPelaksanaanBody = tanggalPelaksanaan.toRequestBody()
                    val kontakBody = kontak.toRequestBody()
                    val hargaBody = harga.toRequestBody()

                    val imagePart = imageUri?.let {
                        val contentResolver = context.contentResolver
                        val type = contentResolver.getType(it) ?: "image/*"
                        val inputStream = contentResolver.openInputStream(it)
                        val bytes = inputStream?.readBytes() ?: byteArrayOf()
                        inputStream?.close()

                        val requestBody = RequestBody.create(type.toMediaTypeOrNull(), bytes)
                        MultipartBody.Part.createFormData("gambar_sertifikasi", "image.jpg", requestBody)
                    }

                    val response = sertifikasiApiService.postSertifikasi(
                        token = "Bearer $token",
                        instansi = instansiBody,
                        judulSertifiksai = judulSertifiksaiBody,
                        deskripsi = deskripsiBody,
                        tanggalPelaksanaan = tanggalPelaksanaanBody,
                        kontak = kontakBody,
                        harga = hargaBody,
                        image = imagePart
                    )

                    if (response.isSuccessful) {
                        onSucces()
                    } else {
                        onError("Gagal mengirim data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError("Terjadi kesalahan12: ${e.message}")
                }
            } else {
                onError("Token tidak ditemukan")
            }
        }
    }

    private fun String.toRequestBody(): RequestBody =
        RequestBody.create("text/plain".toMediaTypeOrNull(), this)

    fun getSertifikasiById(sertifikasiId: Int) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = sertifikasiApiService.getSertifikasiById("Bearer $token", sertifikasiId)
                    if (response.isSuccessful) {
                        val sertifikasiResponse = response.body()
                        if (sertifikasiResponse?.data != null) {
                            _sertifikasiDetail.value = sertifikasiResponse.data
                        } else {
                            _errorMessage.value = "Data sertifikasi tidak ditemukan."
                        }
                    } else {
                        _errorMessage.value = "Gagal mengambil data sertifikasi: ${response.message()}"
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Terjadi kesalahan13: ${e.message}"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan"
            }
        }
    }

    fun updateSertifikasi(
        context: Context,
        id: Int,
        instansi: String,
        judulSertifiksai: String,
        deskripsi: String,
        tanggalPelaksanaan: String,
        kontak: String,
        harga: String,
        imageUri: Uri? = null,
        onSucces: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val instansiBody = instansi.toRequestBody()
                    val judulSertifiksaiBody = judulSertifiksai.toRequestBody()
                    val deskripsiBody = deskripsi.toRequestBody()
                    val tanggalPelaksanaanBody = tanggalPelaksanaan.toRequestBody()
                    val kontakBody = kontak.toRequestBody()
                    val hargaBody = harga.toRequestBody()

                    val imagePart = imageUri?.let {
                        val contentResolver = context.contentResolver
                        val type = contentResolver.getType(it) ?: "image/*"
                        val inputStream = contentResolver.openInputStream(it)
                        val bytes = inputStream?.readBytes() ?: byteArrayOf()
                        inputStream?.close()

                        val requestBody = RequestBody.create(type.toMediaTypeOrNull(), bytes)
                        MultipartBody.Part.createFormData("gambar_sertifikasi", "image.jpg", requestBody)
                    }

                    val response = sertifikasiApiService.postSertifikasi(
                        token = "Bearer $token",
                        instansi = instansiBody,
                        judulSertifiksai = judulSertifiksaiBody,
                        deskripsi = deskripsiBody,
                        tanggalPelaksanaan = tanggalPelaksanaanBody,
                        kontak = kontakBody,
                        harga = hargaBody,
                        image = imagePart
                    )

                    if (response.isSuccessful) {
                        onSucces()
                    } else {
                        onError("Gagal mengirim data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError("Terjadi kesalahan: ${e.message}")
                }
            } else {
                onError("Token tidak ditemukan")
            }
        }
    }

}