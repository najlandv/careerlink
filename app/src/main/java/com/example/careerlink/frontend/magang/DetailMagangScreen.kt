package com.example.careerlink.frontend.magang


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
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
import com.example.careerlink.viewmodels.MagangViewModel

@Composable
fun DetailMagangScreen(
    modifier: Modifier = Modifier,
    magangId: Int,
    viewModel: MagangViewModel = hiltViewModel(),
    navController: NavController
) {

    val magang by viewModel.magangDetail.collectAsState()
    val baseUrl = BuildConfig.BASE_URL
    var imageUrl by remember { mutableStateOf("") }

    if (!magang?.gambarMagang.isNullOrEmpty()) {
        imageUrl = baseUrl + magang!!.gambarMagang
    }

    LaunchedEffect(magangId) {
        viewModel.getMagangById(magangId)
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Detail Magang",
                onBackClick = { navController.popBackStack() }
            )
        },
        content = { paddingValues ->
            if (magang == null) {
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
                            penulis = magang?.Pengguna?.namaLengkap ?: "Unkown" ,
                            perusahaan = magang?.perusahaan ?: "Nama Perusahaan Tidak tersedia",
                            alamat = magang?.alamat ?: "Alamat Tidak Tersedia",
                            kualifikasi = magang?.kualifikasi ?: "Kualifikasi Tidak tersedia",
                            posisi = magang?.posisiMagang ?: "Posisi magang kosong",
                            deskripsi = magang?.deskripsiMagang ?: "Tidak ada deskripsi",
                            judul = magang?.judulMagang ?: "Tidak ada judul",
                            durasi = magang?.durasiMagang ?: "",
                            kontak = magang?.kontak ?: "",
                            gambar = imageUrl
                        )
                    }

                }
            }

        }
    )
}


