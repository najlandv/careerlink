package com.example.careerlink.frontend.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careerlink.R

@Composable
fun CardPopUp(modifier: Modifier = Modifier, onConfirm: () -> Unit, onCancel: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(16.dp)
            .border(2.dp, colorResource(id = R.color.main_yellow), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, shape = CircleShape)
                    .border(BorderStroke(2.dp, colorResource(R.color.button_blue)), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "!",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Apakah kamu yakin ingin menghapus data ini?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ButtonAction(
                    onClick={},
                    text = "Hapus",
                    backgroundColor = colorResource(id = R.color.button_blue),
                    textColor = colorResource(id = R.color.white),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = { },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = colorResource(R.color.button_blue)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, colorResource(R.color.button_blue))
                ) {
                    Text(text = "Batalkan", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

