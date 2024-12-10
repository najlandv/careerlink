package com.example.careerlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerlink.data.TokenDataStore
import com.example.careerlink.models.LoginRequest
import com.example.careerlink.models.RegisterRequest
import com.example.careerlink.services.AuthApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authApiService: AuthApiService,
    private val tokenDataStore: TokenDataStore
) : ViewModel() {

    // State untuk login dan register
    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    // Fungsi Login
    fun login(email: String, password: String) = viewModelScope.launch {
        try {
            _authState.value = AuthState.Loading
            val loginResponse = authApiService.login(
                LoginRequest(email, password)
            )
            tokenDataStore.saveToken(loginResponse.accessToken)
            _authState.value = AuthState.LoginSuccess
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Login gagal")
        }
    }

    // Fungsi Register
    fun register(
        email: String,
        password: String,
        confirmPassword: String,
        fullName: String,
        username: String
    ) = viewModelScope.launch {
        // Validasi input
        if (!isValidInput(email, password, confirmPassword, fullName, username)) return@launch

        try {
            _authState.value = AuthState.Loading
            val registerResponse = authApiService.register(
                RegisterRequest(
                    email = email,
                    password = password,
                    confirmPassword = confirmPassword,
                    fullName = fullName,
                    username = username
                )
            )
            _authState.value = AuthState.RegisterSuccess(registerResponse.message)
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Registrasi gagal")
        }
    }

    // Fungsi Logout
    fun logout() = viewModelScope.launch {
        tokenDataStore.clearToken()
        _authState.value = AuthState.Logout
    }

    // Validasi input register
    private fun isValidInput(
        email: String,
        password: String,
        confirmPassword: String,
        fullName: String,
        username: String
    ): Boolean {
        return when {
            email.isBlank() -> {
                _authState.value = AuthState.Error("Email tidak boleh kosong")
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _authState.value = AuthState.Error("Format email tidak valid")
                false
            }
            fullName.isBlank() -> {
                _authState.value = AuthState.Error("Nama lengkap tidak boleh kosong")
                false
            }
            username.isBlank() -> {
                _authState.value = AuthState.Error("Nama pengguna tidak boleh kosong")
                false
            }
            password.length < 6 -> {
                _authState.value = AuthState.Error("Password minimal 6 karakter")
                false
            }
            password != confirmPassword -> {
                _authState.value = AuthState.Error("Konfirmasi password tidak cocok")
                false
            }
            else -> true
        }
    }
}

// State untuk autentikasi yang mencakup login dan register
sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    object LoginSuccess : AuthState()
    data class RegisterSuccess(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
    object Logout : AuthState()
}