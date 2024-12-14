package com.example.careerlink.frontend.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careerlink.R
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.MainTopBar
import com.example.careerlink.viewmodels.PenggunaViewModel

@Composable
fun HomeScreen(
    viewModel: PenggunaViewModel = hiltViewModel(),
    navController: NavController
) {

    val pengguna by viewModel.pengguna.collectAsState()
//    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = { BottomBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Hallo ${pengguna?.namaLengkap}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.button_blue)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Selamat Datang di CareerLink!\nMari mulai perjalanan kariermu!",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Start
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FeatureCard("Loker", R.drawable.baseline_account_circle_24)
                        Spacer(modifier = Modifier.width(16.dp))
                        FeatureCard("Magang", R.drawable.baseline_post_add_24)

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FeatureCard("Sertifikasi", R.drawable.baseline_verified_24)
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureCard(title: String, iconRes: Int) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(160.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.button_yellow))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            androidx.compose.material3.Icon(
                painter = androidx.compose.ui.res.painterResource(id = iconRes),
                contentDescription = title,
                tint = Color.Black,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}
