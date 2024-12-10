package com.example.careerlink.frontend.sertifikasi

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.careerlink.R
import com.example.careerlink.frontend.component.ButtonAction
import com.example.careerlink.frontend.component.CardAction
import com.example.careerlink.frontend.component.TopBar

data class SertifikasiData(
    val title: String,
    val description: String,
    val date: String
)

@Composable
fun ListSertifikasiScreen(
    modifier: Modifier = Modifier,
    onEditSertifikasi: (SertifikasiData) -> Unit
) {
    var sertifikasiList by remember {
        mutableStateOf(
            listOf(
                SertifikasiData(
                    title = "Certified Kotlin Developer",
                    description = "Sertifikasi terkait penguasaan bahasa Kotlin.",
                    date = "2024-02-03"
                ),
                SertifikasiData(
                    title = "Professional Android Developer",
                    description = "Sertifikasi pengembangan aplikasi Android.",
                    date = "2023-12-15"
                ),
                SertifikasiData(
                    title = "Cloud Computing Specialist",
                    description = "Sertifikasi penggunaan layanan cloud.",
                    date = "2023-11-20"
                )
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar(
            text = "Postingan Saya",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            sertifikasiList.forEach { sertifikasi ->
                CardAction(
                    title = sertifikasi.title,
                    subtitle = "Deskripsi:",
                    desk = sertifikasi.description,
                    date = sertifikasi.date,
                    onEdit = { onEditSertifikasi(sertifikasi) },
                    onDeleteConfirmed = {
                        sertifikasiList = sertifikasiList.toMutableList().apply {
                            remove(sertifikasi)
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListSertifikasiScreenPrev() {
    ListSertifikasiScreen(onEditSertifikasi = {})
}
