package com.example.careerlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerlink.data.TokenDataStore
import com.example.careerlink.models.PenggunaData
import com.example.careerlink.services.PenggunaApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PenggunaViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val penggunaApiService: PenggunaApiService
) : ViewModel() {

    private val _pengguna = MutableStateFlow<PenggunaData?>(null)
    val pengguna: StateFlow<PenggunaData?> = _pengguna

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getPengguna()
    }

    fun getPengguna() {
        viewModelScope.launch {
            val token = tokenDataStore.accessToken.firstOrNull()
            if (!token.isNullOrEmpty()) {
                try {
                    val response = penggunaApiService.getProfile("Bearer $token")
                    _pengguna.value = response.data
                } catch (e: Exception) {
                    e.printStackTrace()
                    _errorMessage.value = "Gagal mengambil data pengguna"
                }
            } else {
                _errorMessage.value = "Token tidak ditemukan"
            }
        }
    }

}