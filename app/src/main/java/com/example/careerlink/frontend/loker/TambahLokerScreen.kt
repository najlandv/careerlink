package com.example.careerlink.frontend.loker

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.careerlink.R
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.LokerViewModel

@Composable
fun TambahLokerScreen(
    modifier: Modifier = Modifier,
    viewModel: LokerViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    navController: NavController
    ) {

    var perusahaan by remember { mutableStateOf("") }
    var judulLoker by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var posisiLoker by remember { mutableStateOf("") }
    var kualifikasi by remember { mutableStateOf("") }
    var jenisLoker by remember { mutableStateOf("") }
    var deskripsiLoker by remember { mutableStateOf("") }
    var kontak by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold(
        topBar = {
            TopBar("Lowongan Baru")
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            item {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Perusahaan",
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = perusahaan,
                        onValueChange = { perusahaan = it },
                        placeholder = { "Nama Perusahaan" },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFC107),
                            unfocusedBorderColor = Color(0xFFFFC107)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Judul Lowongan Kerja",

                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = judulLoker,
                        onValueChange = { judulLoker = it },
                        placeholder = { "Judul Lowongan Kerja" },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFC107),
                            unfocusedBorderColor = Color(0xFFFFC107)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Alamat",

                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = alamat,
                        onValueChange = { alamat = it },
                        placeholder = { "Alamat" },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFC107),
                            unfocusedBorderColor = Color(0xFFFFC107)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Posisi",

                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = posisiLoker,
                        onValueChange = { posisiLoker = it },
                        placeholder = { "Posisi" },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFC107),
                            unfocusedBorderColor = Color(0xFFFFC107)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Kualifikasi",

                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = kualifikasi,
                        onValueChange = { kualifikasi = it },
                        placeholder = { "Kualifikas" },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFC107),
                            unfocusedBorderColor = Color(0xFFFFC107)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Jenis Lowongan Kerja",

                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = jenisLoker,
                        onValueChange = { jenisLoker = it },
                        placeholder = { "Judul Lowongan Kerja" },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFC107),
                            unfocusedBorderColor = Color(0xFFFFC107)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Deskripsi Lowongan Kerja",

                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = deskripsiLoker,
                        onValueChange = { deskripsiLoker = it },
                        placeholder = { "Deskripsi" },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .height(120.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFC107),
                            unfocusedBorderColor = Color(0xFFFFC107)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Kontak",

                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = kontak,
                        onValueChange = { kontak = it },
                        placeholder = { "Kontak" },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .height(120.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFFC107),
                            unfocusedBorderColor = Color(0xFFFFC107)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Upload File",
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .border(
                                1.dp,
                                colorResource(id = R.color.button_yellow_selected),
                                RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                imagePickerLauncher.launch("image/*")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (imageUri != null) {
                            // Jika gambar dipilih, tampilkan preview
                            Image(
                                painter = rememberAsyncImagePainter(model = imageUri),
                                contentDescription = "Selected Image",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_file_upload_24),
                                    contentDescription = "Upload File",
                                    tint = Color.Black,
                                    modifier = Modifier.size(28.dp)
                                )
                                Text(
                                    text = "Upload a File",
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            viewModel.postLoker(
                                context = context,
                                perusahaan = perusahaan,
                                judulLoker = judulLoker,
                                alamat = alamat,
                                posisiLoker = posisiLoker,
                                kualifikasi = kualifikasi,
                                jenisLoker = jenisLoker,
                                deskripsiLoker = deskripsiLoker,
                                kontak = kontak,
                                imageUri = imageUri,
                                onSuccess = {
                                    Toast.makeText(context, "Loker berhasil dikirim", Toast.LENGTH_SHORT).show()
                                    navController.navigate("list-loker-my-post")
                                },
                                onError = { errorMessage ->
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.button_blue))
                    ) {
                        Text(
                            text = "Tambahkan",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }

            }

        }
    }
}

@Preview
@Composable
private fun TambahLokerPrev() {
//    TambahLokerScreen()
}