package com.example.careerlink.frontend.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.careerlink.frontend.login.component.CustomOutlinedTextField
import com.example.careerlink.frontend.login.component.StyledButton

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Masuk",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A73E8),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomOutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = "Nama Pengguna",
            placeholder = "Masukkan nama pengguna"
        )

        CustomOutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = "Password",
            placeholder = "Password"
        )

        StyledButton(
            text = "Masuk",
            onClick = { /* Tambahkan logika untuk login di sini */ }
        )
    }
}

@Preview
@Composable
private fun LoginPrev() {
    LoginScreen()
}
