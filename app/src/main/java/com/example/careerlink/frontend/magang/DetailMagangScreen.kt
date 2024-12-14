package com.example.careerlink.frontend.magang

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.careerlink.R
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.MagangViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMagangScreen(
    modifier: Modifier = Modifier,
    magangId: Int,
    viewModel: MagangViewModel = hiltViewModel(),
    navController: NavController
) {

    val magang by viewModel.magangDetail.collectAsState()
    val baseUrl = "https://n6j4w26m-3000.asse.devtunnels.ms/"

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
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                if (magang == null) {
                    Text(
                        text = "Loading...",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = magang?.tanggalPosting ?: "Tanggal tidak tersedia",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Light
                        )

                        Text(
                            text = magang?.judulMagang ?: "Tidak ada judul",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.button_blue)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Deskripsi Magang:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Text(
                            text = magang?.deskripsiMagang ?: "Tidak ada deskripsi",
                            fontSize = 14.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (!magang?.gambarMagang.isNullOrEmpty()) {
                            val imageUrl = baseUrl + magang!!.gambarMagang
                            Image(
                                painter = rememberAsyncImagePainter(model = imageUrl),
                                contentDescription = "Magang Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun DetailMagangScreenPreview() {
//    DetailMagangScreen()
//}
