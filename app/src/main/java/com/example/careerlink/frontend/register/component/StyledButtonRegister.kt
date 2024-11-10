package com.example.careerlink.frontend.register.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StyledButtonRegister(
    modifier: Modifier = Modifier,
    text: String,  // Label untuk tombol
    onClick: () -> Unit  // Aksi ketika tombol ditekan
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8)) // Ubah warna tombol sesuai tema
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun StyledButtonPrev() {
    StyledButtonRegister(
        text = "Daftar",  // Ganti label tombol untuk form registrasi
        onClick = { /* Tambahkan logika registrasi di sini */ }
    )
}
