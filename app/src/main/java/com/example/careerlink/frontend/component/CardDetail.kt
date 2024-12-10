package com.example.careerlink.frontend.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careerlink.R

@Composable
fun CardDetail(modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color(0xFFFFDE59)),
    ) {
        Column(modifier = Modifier.padding(24.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start){
                Icon(Icons.Default.AccountCircle, "Proile", modifier = Modifier.size(48.dp))
                Spacer(modifier =  Modifier.width(8.dp))
                Column {
                    Text("Najla Humaira",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                    Text("9 Juli 2020")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("PT SEJAHTERA MANDIRI",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                )
            Spacer(modifier = Modifier.height(24.dp))
                Card(colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Posisi :")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Ahli K3 (Keselamatan dan Kesehatan Kerja)")
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Deskripsi Pekerjaan :")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Seseorang yang bekerja di Bagian K3 bertugas menjamin dan melindungi keselamatan serta kesehatan tenaga kerja melalui berbagai upaya keamanan pekerja. Beberapa hal yang mungkin bisa dilakukan adalah pencegahan kecelakaan seperti kebakaran, cedera ataupun hal-hal lain yang mungkin bisa membahayakan.")
                    }
                }
            Spacer(modifier = Modifier.height(8.dp))
            Image(painterResource(R.drawable.gambar_contoh),"Test", modifier = Modifier.size(250.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.button_blue))
            ) {
                Text(
                    text = "Review",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardDetailPrev() {
    CardDetail()
}