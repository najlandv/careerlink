package com.example.careerlink

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
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

        Button(
            onClick = { /* Handle Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
        ) {
            Text(
                text = "Masuk",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
