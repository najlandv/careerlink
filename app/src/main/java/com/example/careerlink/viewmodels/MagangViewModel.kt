package com.example.careerlink.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerlink.data.TokenDataStore
import com.example.careerlink.models.MagangData
import com.example.careerlink.services.MagangApiService
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
class MagangViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val magangApiService: MagangApiService
) : ViewModel() {

    private val _magangList = MutableStateFlow<List<MagangData>>(emptyList())
    val magangList: StateFlow<List<MagangData>> = _magangList

    private val _myPostMagangList = MutableStateFlow<List<MagangData>>(emptyList())
    val myPostMagangList: StateFlow<List<MagangData>> = _myPostMagangList

    private val _magangDetail = MutableStateFlow<MagangData?>(null)
    val magangDetail: StateFlow<MagangData?> = _magangDetail

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchMagangList()
        fetchMyPostMagangList()
    }

    private fun fetchMagangList() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = magangApiService.getMagangList("Bearer $token")
                    _magangList.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Data tidak ditemukan"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan."
            }
        }
    }

    fun fetchMyPostMagangList() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = magangApiService.getMyPostMagangList("Bearer $token")
                    _myPostMagangList.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Data tidak ditemukan"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan"
            }
        }
    }

    fun deleteMagang(
        id: Int,
        onSucces: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = magangApiService.deleteMagang("Bearer $token", id)
                    if (response.isSuccessful) {
                        fetchMagangList()
                        fetchMyPostMagangList()
                        onSucces()
                    } else {
                        onError("Gagal menghapus data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onError("Terjadi kesalahan: ${e.message}")
                }
            }
        }
    }

    fun postMagang(
        context: Context,
        perusahaan: String,
        judulMagang: String,
        alamat: String,
        posisiMagang: String,
        kualifikasi: String,
        jenisMagang: String,
        durasiMagang: String,
        deskripsiMagang: String,
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
                    val judulMagangBody = judulMagang.toRequestBody()
                    val alamatBody = alamat.toRequestBody()
                    val posisiMagangBody = posisiMagang.toRequestBody()
                    val kualifikasiBody = kualifikasi.toRequestBody()
                    val jenisMagangBody = jenisMagang.toRequestBody()
                    val durasiMagangBody = durasiMagang.toRequestBody()
                    val deskripsiMagangBody = deskripsiMagang.toRequestBody()
                    val kontakBody = kontak.toRequestBody()

                    val imagePart = imageUri?.let {
                        val contentResolver = context.contentResolver
                        val type = contentResolver.getType(it) ?: "image/*"
                        val inputStream = contentResolver.openInputStream(it)
                        val bytes = inputStream?.readBytes() ?: byteArrayOf()
                        inputStream?.close()

                        val requestBody = RequestBody.create(type.toMediaTypeOrNull(), bytes)
                        MultipartBody.Part.createFormData("gambar_magang", "image.jpg", requestBody)
                    }

                    val response = magangApiService.postMagang(
                        token = "Bearer $token",
                        perusahaan = perusahaanBody,
                        judulMagang = judulMagangBody,
                        alamat = alamatBody,
                        posisiMagang = posisiMagangBody,
                        kualifikasi = kualifikasiBody,
                        jenisMagang = jenisMagangBody,
                        durasiMagang = durasiMagangBody,
                        deskripsiMagang = deskripsiMagangBody,
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

    fun getMagangById(magangId: Int) {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = magangApiService.getMagangById("Bearer $token", magangId)
                    if (response.isSuccessful) {
                        val magangResponse = response.body()
                        if (magangResponse?.data != null) {
                            _magangDetail.value = magangResponse.data
                        } else {
                            _errorMessage.value = "Data magang tidak ditemukan."
                        }
                    } else {
                        _errorMessage.value = "Gagal mengambil data magang: ${response.message()}"
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Terjadi kesalahan: ${e.message}"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan"
            }
        }
    }

    fun updateMagang(
        context: Context,
        id: Int,
        perusahaan: String,
        judulMagang: String,
        alamat: String,
        posisiMagang: String,
        kualifikasi: String,
        jenisMagang: String,
        durasiMagang: String,
        deskripsiMagang: String,
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
                    val judulMagangBody = judulMagang.toRequestBody()
                    val alamatBody = alamat.toRequestBody()
                    val posisiMagangBody = posisiMagang.toRequestBody()
                    val kualifikasiBody = kualifikasi.toRequestBody()
                    val jenisMagangBody = jenisMagang.toRequestBody()
                    val durasiMagangBody = durasiMagang.toRequestBody()
                    val deskripsiMagangBody = deskripsiMagang.toRequestBody()
                    val kontakBody = kontak.toRequestBody()

                    val imagePart = imageUri?.let {
                        val contentResolver = context.contentResolver
                        val type = contentResolver.getType(it) ?: "image/*"
                        val inputStream = contentResolver.openInputStream(it)
                        val bytes = inputStream?.readBytes() ?: byteArrayOf()
                        inputStream?.close()

                        val requestBody = RequestBody.create(type.toMediaTypeOrNull(), bytes)
                        MultipartBody.Part.createFormData("gambar_magang", "image.jpg", requestBody)
                    }

                    val response = magangApiService.updateMagang(
                        token = "Bearer $token",
                        id = id,
                        perusahaan = perusahaanBody,
                        judulMagang = judulMagangBody,
                        alamat = alamatBody,
                        posisiMagang = posisiMagangBody,
                        kualifikasi = kualifikasiBody,
                        jenisMagang = jenisMagangBody,
                        durasiMagang = durasiMagangBody,
                        deskripsiMagang = deskripsiMagangBody,
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