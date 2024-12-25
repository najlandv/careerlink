package com.example.careerlink.frontend.sertifikasi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.careerlink.BuildConfig
import com.example.careerlink.frontend.component.CardList
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.SertifikasiViewModel

@Composable
fun ListSertifikasiScreen(
    modifier: Modifier = Modifier,
    viewModel: SertifikasiViewModel = hiltViewModel(),
    navController: NavController
) {
    val sertifikasiList by viewModel.sertifikasiList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val baseUrl = BuildConfig.BASE_URL

    Scaffold(
        topBar = {
            TopBar(
                text = "Sertifikasi",
                onBackClick = { navController.popBackStack() }
            )
        },
        content = { paddingValues ->
            if (!errorMessage.isNullOrEmpty()) {
                Text(
                    text = errorMessage!!,
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                )
            } else if (sertifikasiList.isEmpty()) {
                Text(
                    text = "Tidak ada data Sertifikasi tersedia.",
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(sertifikasiList) { sertifikasi ->
                        val imageUrl = if (!sertifikasi.gambarSertifikasi.isNullOrEmpty()) {
                            baseUrl + sertifikasi.gambarSertifikasi
                        } else {
                            ""
                        }

                        CardList(
                            title = sertifikasi.judulSertifikasi,
                            subtitle = "Deskripsi:",
                            desk = sertifikasi.deskripsi,
                            date = sertifikasi.tanggalPosting,
                            gambar = imageUrl,
                            onClick = { navController.navigate("detail-sertifikasi/${sertifikasi.idSertifikasi}") }
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
    val navController = rememberNavController()
    ListSertifikasiScreen(navController = navController)
}
