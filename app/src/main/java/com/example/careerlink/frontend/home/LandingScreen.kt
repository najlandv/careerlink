package com.example.careerlink.frontend.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careerlink.R
import com.example.careerlink.frontend.component.ButtonAction

@Composable
fun LandingScreen() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.careerlink_utama_1),
                contentDescription = ("logo home"),
                modifier = Modifier.size(320.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            androidx.compose.material3.Text(
                text = "CAREERLINK",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.compose.ui.graphics.Color(0xFFFFC107)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        androidx.compose.material3.Text(
            text = "Selamat Datang!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        androidx.compose.material3.Text(
            text = "Temukan peluang karier terbaik dan mulai suksesmu sekarang!",
            fontSize = 16.sp,
            color = androidx.compose.ui.graphics.Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            ButtonAction(
                text = "Daftar",
                backgroundColor = colorResource(R.color.button_blue),
                textColor = androidx.compose.ui.graphics.Color.White,
                onClick = {  }
            )
            Spacer(modifier = Modifier.width(16.dp))
            ButtonAction(
                text = "Masuk",
                backgroundColor = androidx.compose.ui.graphics.Color(0xFFCCCCCC),
                textColor = androidx.compose.ui.graphics.Color.Black,
                onClick = { }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun LandingScreenPrev() {
    LandingScreen()
}