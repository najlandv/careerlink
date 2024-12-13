package com.example.careerlink.frontend.sertifikasi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import com.example.careerlink.frontend.component.CardList
import com.example.careerlink.frontend.component.TopBar

data class CertificationData(
    val title: String,
    val description: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListSertifikasiScreen(
    modifier: Modifier = Modifier
) {
    val sertifikasiList = listOf(
        CertificationData(
            title = "Certified Kotlin Developer",
            description = "Sertifikasi penguasaan bahasa pemrograman Kotlin.",
            date = "2024-02-03"
        ),
        CertificationData(
            title = "Professional Android Developer",
            description = "Sertifikasi pengembangan aplikasi Android.",
            date = "2023-12-15"
        ),
        CertificationData(
            title = "Cloud Computing Specialist",
            description = "Sertifikasi layanan komputasi awan.",
            date = "2023-11-20"
        ),
        CertificationData(
            title = "Data Science Practitioner",
            description = "Sertifikasi dasar-dasar ilmu data.",
            date = "2023-10-10"
        ),
    )

    Scaffold(
        topBar = {
            TopBar(
                text = "Sertifikasi",
                onBackClick = { }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(sertifikasiList) { sertifikasi ->
                        CardList(
                            title = sertifikasi.title,
                            subtitle = "Deskripsi:",
                            desk = sertifikasi.description,
                            date = sertifikasi.date
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ListSertifikasiScreenPrev() {
    ListSertifikasiScreen()
}
