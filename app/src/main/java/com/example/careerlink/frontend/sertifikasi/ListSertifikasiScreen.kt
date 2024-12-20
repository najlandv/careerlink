package com.example.careerlink.frontend.sertifikasi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
                        modifier = Modifier.padding(paddingValues).padding(16.dp)
                    )
                } else if (sertifikasiList.isEmpty()) {
                    Text(
                        text = "Tidak ada data Magang tersedia.",
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(sertifikasiList) { sertifikasi ->
                            CardList(
                                title = sertifikasi.judulSertifikasi,
                                subtitle = "Deskripsi:",
                                desk = sertifikasi.deskripsi,
                                date = sertifikasi.tanggalPosting,
                                onClick = {navController.navigate("detail-sertifikasi/${sertifikasi.idSertifikasi}")}
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
//    ListSertifikasiScreen()
}
