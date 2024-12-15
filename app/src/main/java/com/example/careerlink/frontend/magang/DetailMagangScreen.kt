package com.example.careerlink.frontend.magang

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
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
import com.example.careerlink.frontend.component.CardDetail
import com.example.careerlink.frontend.component.TopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMagangScreen(
    modifier: Modifier = Modifier,
    magangTitle: String,
    magangDescription: String,
    magangDate: String,
    magangImage: Int = R.drawable.ic_launcher_background,
    onBack: () -> Unit,
    onReviewClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                text = "Detail Magang",
                onBackClick = onBack
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                CardDetail(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    Title = magangTitle,
                    Description = magangDescription,
                    Date = magangDate,
                    Image = magangImage,
                    onReviewClick = onReviewClick
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailMagangScreenPreview() {
    DetailMagangScreen(
        magangTitle = "Front End Developer",
        magangDescription = "Job desc: membuat tampilan frontend yang sesuai dengan desain, masa magang 5 bulan.",
        magangDate = "2024-01-07",
        magangImage = R.drawable.ic_launcher_background,
        onBack = {},
        onReviewClick = {}
    )
}
