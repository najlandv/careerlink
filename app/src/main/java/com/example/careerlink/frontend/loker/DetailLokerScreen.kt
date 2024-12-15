package com.example.careerlink.frontend.loker

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.careerlink.BuildConfig
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.CardDetail
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.LokerViewModel

@Composable
fun DetailLokerScreen(
    modifier: Modifier = Modifier,
    lokerId: Int,
    viewModel: LokerViewModel = hiltViewModel(),
    navController: NavController
) {
    val loker by viewModel.lokerDetail.collectAsState()
    val baseUrl = BuildConfig.BASE_URL

    LaunchedEffect(lokerId) {
        viewModel.getLokerById(lokerId)
    }

    var imageUrl by remember { mutableStateOf("") }

    if (loker!!.gambar_loker != null) {
        imageUrl = baseUrl + loker!!.gambar_loker
    }

    Scaffold(
        topBar = {
            TopBar("Edit Postingan Lowongan Kerja", onBackClick = { navController.popBackStack() })
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                CardDetail(
                    penulis = loker!!.Pengguna.namaLengkap,
                    perusahaan = loker!!.perusahaan,
                    posisi = loker!!.posisi_loker,
                    deskripsi = loker!!.deskripsi_loker,
                    gambar = imageUrl
                )
            }

        }
    }
}

@Preview
@Composable
private fun DetailLokerPrev() {
    val navController = rememberNavController()
    val lokerId = 3
    DetailLokerScreen(navController = navController, lokerId = lokerId)
}