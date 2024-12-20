package com.example.careerlink.frontend.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.careerlink.R
import com.example.careerlink.services.LocationResult

@Composable
fun CardDetail(
    modifier: Modifier = Modifier,
    penulis: String = "Penulis",
    perusahaan: String = "",
    judul: String ="",
    kualifikasi: String ="",
    alamat: String="",
    posisi: String = "",
    deskripsi: String = "",
    durasi: String = "",
    kontak: String = "",
    harga: String = "",
    gambar: String? = null
) {

    Card(modifier = Modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color(0xFFFFDE59)),
    ) {
        val context = LocalContext.current

        Column(modifier = Modifier.padding(24.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start){
                Icon(Icons.Default.AccountCircle, "Proile", modifier = Modifier.size(48.dp))
                Spacer(modifier =  Modifier.width(8.dp))
                Column {
                    Text(penulis,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
//                    Text("9 Juli 2020")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(perusahaan,
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
                        Text(judul)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Posisi :")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(posisi)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Deskripsi :")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(deskripsi)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(kualifikasi)
                        if (durasi !== ""){
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Durasi Magang :")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(durasi)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Kontak :")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(kontak)
                        }
                        if (harga !== ""){
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Harga :")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(harga)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Alamat :")
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = alamat,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    // Redirect to Google Maps
                                    val uri = Uri.parse("geo:?q=${alamat}")
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    intent.setPackage("com.google.android.apps.maps")
                                    context.startActivity(intent)
                                })
                    }
                }
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                rememberAsyncImagePainter(model = gambar),
                "Test",
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun CardDetailPrev() {
//    CardDetail()
//}