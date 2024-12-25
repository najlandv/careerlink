package com.example.careerlink.frontend.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import coil.compose.rememberAsyncImagePainter

@Composable
fun CardList(
    title: String = "PT SEMEN PADANG",
    subtitle: String = "Posisi Magang :",
    desk: String = "Deksripsi Magang yang akan dilakukan selama prosesnya",
    date: String = "12-12-1212",
    gambar: String? = null,
    onClick: () -> Unit = { println("clicked") }
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(Color(0xFFFFDE59)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Menampilkan gambar jika ada
            if (!gambar.isNullOrEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(model = gambar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp) // Ukuran gambar lebih besar
                        .clip(RoundedCornerShape(8.dp)) // Membuat gambar berbentuk petak dengan sudut membulat kecil
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    subtitle,
                    style = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp
                    )
                )
                Text(
                    desk,
                    style = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    date,
                    style = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}
