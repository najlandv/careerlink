package com.example.careerlink.frontend.magang

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
import com.example.careerlink.frontend.component.CardList
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.MagangViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListMagangScreen(
    modifier: Modifier = Modifier,
    viewModel: MagangViewModel = hiltViewModel()
) {

    val magangList by viewModel.magangList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                text = "Magang",
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
                if (!errorMessage.isNullOrEmpty()) {
                    Text(
                        text = errorMessage!!,
                        modifier = Modifier.padding(paddingValues).padding(16.dp)
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
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(magangList) { magang ->
                            CardList(
                                title = magang.judulMagang,
                                subtitle = "${magang.perusahaan} - ${magang.alamat}",
                                desk = magang.deskripsiMagang,
                                date = magang.tanggalPosting
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ListMagangScreenPrev() {
    ListMagangScreen()
}
