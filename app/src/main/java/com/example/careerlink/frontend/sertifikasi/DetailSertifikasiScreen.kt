package com.example.careerlink.frontend.sertifikasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import com.example.careerlink.viewmodels.SertifikasiViewModel

@Composable
fun DetailSertifikasiScreen(
    modifier: Modifier = Modifier,
    sertifikasiId: Int,
    viewModel: SertifikasiViewModel = hiltViewModel(),
    navController: NavController
) {
    val sertifikasi by viewModel.sertifikasiDetail.collectAsState()
    val baseUrl = "https://n6j4w26m-3000.asse.devtunnels.ms/"

    LaunchedEffect(sertifikasiId) {
        viewModel.getSertifikasiById(sertifikasiId)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar(
            text = "Detail Sertifikasi",
            onBackClick = { navController.popBackStack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (sertifikasi == null) {
            // Tampilkan placeholder saat data belum tersedia
            Text(
                text = "Loading...",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        } else {
            // Tampilkan detail sertifikasi
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = sertifikasi?.tanggalPelaksanaan ?: "Tanggal tidak tersedia",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Text(
                    text = sertifikasi?.judulSertifikasi ?: "Judul tidak tersedia",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.button_blue)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Deskripsi Sertifikasi:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = sertifikasi?.deskripsi ?: "Deskripsi tidak tersedia",
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Jika gambar tersedia, tampilkan
                if (!sertifikasi?.gambarSertifikasi.isNullOrEmpty()) {
                    val imageUrl = baseUrl + sertifikasi!!.gambarSertifikasi
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = "Sertifikasi Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailSertifikasiScreenPreview() {
//    DetailSertifikasiScreen(
//        sertifikasiTitle = "Certified Financial Advisory",
//        sertifikasiDescription = "Pelajari perencanaan investasi, manajemen risiko, serta strategi keuangan untuk klien individu dan perusahaan. Sertifikasi ini memberikan pengakuan global dan memperluas peluang karier Anda di dunia keuangan.",
//        sertifikasiDate = "2024-01-07",
//        sertifikasiImage = R.drawable.ic_launcher_background,
//        onBack = {}
//    )
//}