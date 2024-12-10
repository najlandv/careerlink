package com.example.careerlink.frontend.register.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careerlink.frontend.register.component.CustomOutlinedTextRegister
import com.example.careerlink.frontend.register.component.StyledButtonRegister
import com.example.careerlink.viewmodels.AuthState
import com.example.careerlink.viewmodels.AuthViewModel

@Composable
fun RegisterScreen(modifier: Modifier = Modifier, viewModel: AuthViewModel = hiltViewModel(), navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    val registerState by viewModel.authState.collectAsState()

    // Efek samping untuk navigasi saat registrasi berhasil
    LaunchedEffect(registerState) {
        when (registerState) {
            is AuthState.RegisterSuccess -> {
                // Navigasi ke halaman login
                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
            }
            is AuthState.Error -> {
                // Opsional: Tampilkan pesan error dengan Snackbar atau dialog
            }
            else -> {}
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Buat Akun",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A73E8),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Field for Full Name
        CustomOutlinedTextRegister(
            value = fullName,
            onValueChange = { value -> fullName = value },
            label = "Nama Lengkap",
            placeholder = "Masukkan nama lengkap"
        )

        // Field for Username
        CustomOutlinedTextRegister(
            value = username,
            onValueChange = { value -> username = value },
            label = "Nama Pengguna",
            placeholder = "Masukkan nama pengguna"
        )

        // Field for Email
        CustomOutlinedTextRegister(
            value = email,
            onValueChange = { value ->
                email = value
            },
            label = "Email",
            placeholder = "Masukkan email"
        )

        // Field for Password
        CustomOutlinedTextRegister(
            value = password,
            onValueChange = { value -> password = value },
            label = "Password",
            placeholder = "Masukkan password",
        )

        // Field for Confirm Password
        CustomOutlinedTextRegister(
            value = confirmPassword,
            onValueChange = { value ->
                confirmPassword = value
            },
            label = "Konfirmasi Password",
            placeholder = "Masukkan ulang password"
        )

        StyledButtonRegister(
            text = "Daftar",
            onClick = { viewModel.register(
                email,
                password,
                confirmPassword,
                fullName,
                username
            )  }
        )
    }
}

@Preview
@Composable
private fun RegisterPrev() {
//    RegisterScreen()
}
