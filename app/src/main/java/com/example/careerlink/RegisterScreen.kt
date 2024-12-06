package com.example.careerlink

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Validation states
    var isFullNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordMatchError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
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

        OutlinedTextField(
            value = fullName,
            onValueChange = {
                fullName = it
                isFullNameError = it.isBlank()
            },
            label = { Text("Nama Lengkap") },
            placeholder = { Text("Masukkan nama lengkapmu") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            isError = isFullNameError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFFC107),
                unfocusedBorderColor = Color(0xFFFFC107)
            )
        )
        if (isFullNameError) {
            Text("Nama lengkap tidak boleh kosong", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nama Pengguna") },
            placeholder = { Text("Masukkan nama pengguna") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFFC107),
                unfocusedBorderColor = Color(0xFFFFC107)
            )
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isEmailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            label = { Text("Email") },
            placeholder = { Text("Masukkan email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            isError = isEmailError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFFC107),
                unfocusedBorderColor = Color(0xFFFFC107)
            )
        )
        if (isEmailError) {
            Text("Masukkan email yang valid", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Masukkan password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFFC107),
                unfocusedBorderColor = Color(0xFFFFC107)
            )
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                isPasswordMatchError = password != confirmPassword
            },
            label = { Text("Konfirmasi Password") },
            placeholder = { Text("Masukkan ulang password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            isError = isPasswordMatchError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFFC107),
                unfocusedBorderColor = Color(0xFFFFC107)
            )
        )
        if (isPasswordMatchError) {
            Text("Password tidak sesuai", color = Color.Red, fontSize = 12.sp)
        }

        Button(
            onClick = {
                // Handle registration logic here
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
        ) {
            Text(
                text = "Daftar",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen()
}
