package com.example.careerlink.frontend.sertifikasi

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.careerlink.R
import com.example.careerlink.frontend.component.ButtonAction
import com.example.careerlink.frontend.component.CardReview
import com.example.careerlink.frontend.component.TopBar

data class ReviewData(
    val username: String,
    val description: String,
    val date: String
)

@Composable
fun ReviewSertifikasiScreen(modifier: Modifier = Modifier) {
    val reviewList = listOf(
        ReviewData(
            username = "Kurniaa saja",
            description = "Sertifikasi di sini bagus banget sihh, recommended pokoknya",
            date = "2024-07-03"
        ),
        ReviewData(
            username = "Divadiva",
            description = "Terimakasih infonya kak",
            date = "2024-03-04"
        ),
        ReviewData(
            username = "Nana",
            description = "Mau daftar juga ahh untuk nambah-nambah ilmu",
            date = "2024-03-03"
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar(
            text = "Review Postingan",
            onBackClick = {  }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonAction(
            text = "Tambah Review",
            backgroundColor = colorResource(R.color.button_blue),
            textColor = Color.White,
            onClick = {  },

        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            reviewList.forEach { review ->
                CardReview(
                    username = review.username,
                    desk = review.description,
                    date = review.date
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewSertifikasiScreenPrev() {
    ReviewSertifikasiScreen()
}
