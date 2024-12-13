package com.example.careerlink.viewmodels

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
                    onError("Terjadi kesalahan: ${e.message}")
                }
            }
        }
    }

}