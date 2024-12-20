package com.example.careerlink.frontend.sertifikasi


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careerlink.BuildConfig
import com.example.careerlink.frontend.component.CardDetail
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.SertifikasiViewModel

@Composable
fun DetailSertifikasiScreen(
    modifier: Modifier = Modifier,
    sertifikasiId: Int,
    viewModel: SertifikasiViewModel = hiltViewModel(),
    navController: NavController
) {
    val sertifikasi by viewModel.sertifikasiDetail.collectAsState()
    val baseUrl = BuildConfig.BASE_URL

    var imageUrl by remember { mutableStateOf("") }

    if (!sertifikasi?.gambarSertifikasi.isNullOrEmpty()) {
        imageUrl = baseUrl + sertifikasi!!.gambarSertifikasi
    }

    LaunchedEffect(sertifikasiId) {
        viewModel.getSertifikasiById(sertifikasiId)
    }
    Scaffold(
        topBar = {
            TopBar(
                text = "Detail Sertifikasi",
                onBackClick = { navController.popBackStack() }
            )
        },
        content = { paddingValues ->
            if (sertifikasi == null) {
                Text(
                    text = "Loading...",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn(
                    modifier = modifier
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp)
                ) {
                    item {

                        CardDetail(
                            penulis = sertifikasi!!.Pengguna.namaLengkap ,
                            perusahaan = sertifikasi?.instansi ?: "Nama Instansi Tidak tersedia",
                            deskripsi = sertifikasi?.deskripsi ?: "Tidak ada deskripsi",
                            judul = sertifikasi?.judulSertifikasi ?: "Judul tidak tersedia",
                            kontak = sertifikasi?.kontak ?: "",
                            harga = sertifikasi?.harga ?: "",
                            gambar = imageUrl
                        )
                    }
                }

            }
        })

}

