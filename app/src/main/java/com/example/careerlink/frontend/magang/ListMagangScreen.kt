package com.example.careerlink.frontend.magang

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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
import com.example.careerlink.viewmodels.MagangViewModel

@Composable
fun ListMagangScreen(
    modifier: Modifier = Modifier,
    viewModel: MagangViewModel = hiltViewModel(),
    navController: NavController
) {
    val magangList by viewModel.magangList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val baseUrl = BuildConfig.BASE_URL

    Scaffold(
        topBar = {
            TopBar(
                text = "Magang",
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
            } else if (magangList.isEmpty()) {
                Text(
                    text = "Tidak ada data Magang tersedia.",
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
                    items(magangList) { magang ->
                        val imageUrl = if (!magang.gambarMagang.isNullOrEmpty()) {
                            baseUrl + magang.gambarMagang
                        } else {
                            ""
                        }

                        CardList(
                            title = magang.judulMagang,
                            subtitle = "${magang.perusahaan} - ${magang.alamat}",
                            desk = magang.deskripsiMagang,
                            date = magang.tanggalPosting,
                            gambar = imageUrl,
                            onClick = { navController.navigate("detail-magang/${magang.idMagang}") }
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ListMagangScreenPrev() {
    val navController = rememberNavController()
    ListMagangScreen(navController = navController)
}
