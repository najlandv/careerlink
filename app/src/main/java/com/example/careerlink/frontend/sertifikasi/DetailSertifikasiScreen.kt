package com.example.careerlink.frontend.sertifikasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.careerlink.R
import com.example.careerlink.frontend.component.TopBar

@Composable
fun DetailSertifikasiScreen(
    modifier: Modifier = Modifier,
    sertifikasiTitle: String,
    sertifikasiDescription: String,
    sertifikasiDate: String,
    sertifikasiImage: Int = R.drawable.ic_launcher_background, // Replace with your image resource
    onBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar(
            text = "Detail Sertifikasi",
            onBackClick = { onBack() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display detail information
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = sertifikasiDate,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light
            )

            Text(
                text = sertifikasiTitle,
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
                text = sertifikasiDescription,
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = sertifikasiImage),
                contentDescription = "Sertifikasi Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Handle review action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.button_blue))
            ) {
                Text(
                    text = "Review",
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailSertifikasiScreenPreview() {
    DetailSertifikasiScreen(
        sertifikasiTitle = "Certified Financial Advisory",
        sertifikasiDescription = "Pelajari perencanaan investasi, manajemen risiko, serta strategi keuangan untuk klien individu dan perusahaan. Sertifikasi ini memberikan pengakuan global dan memperluas peluang karier Anda di dunia keuangan.",
        sertifikasiDate = "2024-01-07",
        sertifikasiImage = R.drawable.ic_launcher_background,
        onBack = {}
    )
}