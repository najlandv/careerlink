package com.example.careerlink.frontend.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardReview(username: String="Rizkaa22", desk: String="Gimana komentarnya magang disana asik kah??", date: String="12-12-1212") {
    Card(modifier = Modifier.padding(16.dp)
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color(0xFFFFDE59)),
    ) {
        Column(modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(username,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(desk,
                    style = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp

                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(date,
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardRiviewPrev() {
    CardReview()
}