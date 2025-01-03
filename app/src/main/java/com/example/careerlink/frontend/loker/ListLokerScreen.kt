package com.example.careerlink.frontend.loker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.CardList
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.LokerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListLokerScreen(
    viewModel: LokerViewModel = hiltViewModel(),
    navController: NavController
) {
    val lokerList by viewModel.lokerList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val baseUrl = BuildConfig.BASE_URL

    Scaffold(
        topBar = {
            TopBar(
                "Lowongan Kerja",
                onBackClick = { navController.popBackStack() },
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage!!,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        } else if (lokerList.isEmpty()) {
            Text(
                text = "Tidak ada data loker tersedia.",
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                items(lokerList) { loker ->
                    val imageUrl = if (!loker.gambar_loker.isNullOrEmpty()) {
                        baseUrl + loker.gambar_loker
                    } else {
                        ""
                    }

                    CardList(
                        title = loker.judul_loker,
                        subtitle = "${loker.perusahaan} - ${loker.alamat}",
                        desk = loker.deskripsi_loker,
                        date = loker.tanggal_posting,
                        gambar = imageUrl,
                        onClick = { navController.navigate("detail-loker/${loker.id_loker}") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListLokerPrev() {
    val navController = rememberNavController()
    ListLokerScreen(navController = navController)
}
