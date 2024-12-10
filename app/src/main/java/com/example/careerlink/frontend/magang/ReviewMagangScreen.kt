package com.example.careerlink.frontend.magang

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.careerlink.R
import com.example.careerlink.frontend.component.CardReview
import com.example.careerlink.frontend.component.TopBar

data class ReviewMagangData(
    val username: String,
    val description: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewMagangScreen(modifier: Modifier = Modifier) {
    var reviewList by remember {
        mutableStateOf(
            listOf(
                ReviewMagangData(
                    username = "Aldi Setiawan",
                    description = "Magangnya seru banget, mentornya membantu banget.",
                    date = "2024-06-10"
                ),
                ReviewMagangData(
                    username = "Fauzan Hakim",
                    description = "Lingkungan kerja di sini sangat mendukung untuk belajar.",
                    date = "2024-04-15"
                ),
                ReviewMagangData(
                    username = "Syifa Nur",
                    description = "Magang ini sangat membantu untuk karier masa depan saya.",
                    date = "2024-05-08"
                )
            )
        )
    }

    var newComment by remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        topBar = {
            TopBar(
                text = "Review Magang",
                onBackClick = { /* Tambahkan aksi kembali jika diperlukan */ }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(reviewList) { review ->
                        CardReview(
                            username = review.username,
                            desk = review.description,
                            date = review.date
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = newComment,
                        onValueChange = { newComment = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Tulis komentar Anda...") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = colorResource(id = R.color.cream)
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.button_blue),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        onClick = {
                            if (newComment.text.isNotBlank()) {
                                reviewList = reviewList + ReviewMagangData(
                                    username = "User Baru",
                                    description = newComment.text,
                                    date = "2024-12-08"
                                )
                                newComment = TextFieldValue()
                            }
                        }
                    ) {
                        Text("Kirim")
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ReviewMagangScreenPrev() {
    ReviewMagangScreen()
}
