import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
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

    Scaffold(
        topBar = {
            TopBar(
                text = "Review Postingan",
                onBackClick = { /* Handle back navigation */ }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ButtonAction(
                    text = "Tambah Review",
                    backgroundColor = colorResource(R.color.button_blue),
                    textColor = Color.White,
                    onClick = { /* Handle add review */ },
                )
            }

            items(reviewList) { review ->
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
