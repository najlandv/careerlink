package com.example.careerlink.frontend.loker

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.careerlink.frontend.component.CardReview
import com.example.careerlink.frontend.component.TopBar

@Composable
fun ListReviewScreen(modifier: Modifier = Modifier) {
    val listReview = listOf(
        Review("Najla Nadiva", "Bekerja sebagai pengembang perangkat lunak", "01 Desember 2024"),
        Review("Humaira","Membuat aplikasi skala besar", "02 Desember 2024"),
        Review("Ica",  "Mendesain antarmuka aplikasi", "03 Desember 2024"),
        Review("Njlndv", "Mendesain antarmuka aplikasi", "03 Desember 2024"),
        Review("Rizkaaa",  "Analisis data untuk pengambilan keputusan", "04 Desember 2024")
    )
    Scaffold(
        topBar = {
            TopBar("Riview Postingan")
        },
        bottomBar = {
            Row (modifier = Modifier.fillMaxWidth()){
                TextField("Add Coment", onValueChange = {}, modifier = Modifier.fillMaxWidth(), suffix = {
                    Icon(Icons.Default.Send,"sent")
                })
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))

            }
            items(listReview) { review ->
                CardReview(
                    username = review.username,
                    desk = review.desk,
                    date = review.date
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

data class Review(
    val username: String,
    val desk: String,
    val date: String
)

@Preview
@Composable
private fun ListReviewPrev() {
    ListReviewScreen()
}