package com.example.careerlink.frontend.magang

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.careerlink.R
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.MagangViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMagangScreen(
    modifier: Modifier = Modifier,
    magangId: Int,
    viewModel: MagangViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    navController: NavController
) {

    val magang by viewModel.magangDetail.collectAsState()
    val baseUrl = "https://n6j4w26m-3000.asse.devtunnels.ms/"

    var perusahaan by remember { mutableStateOf("") }
    var judulMagang by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var posisiMagang by remember { mutableStateOf("") }
    var kualifikasi by remember { mutableStateOf("") }
    var jenisMagang by remember { mutableStateOf("") }
    var deskripsiMagang by remember { mutableStateOf("") }
    var durasiMagang by remember { mutableStateOf("") }
    var kontak by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    LaunchedEffect(magangId) {
        viewModel.getMagangById(magangId)
    }

    LaunchedEffect(magang) {
        if (magang != null) {
            perusahaan = magang!!.perusahaan
            judulMagang = magang!!.judulMagang
            alamat = magang!!.alamat
            posisiMagang = magang!!.posisiMagang
            kualifikasi = magang!!.kualifikasi
            jenisMagang = magang!!.jenisMagang
            durasiMagang = magang!!.durasiMagang
            deskripsiMagang = magang!!.deskripsiMagang
            kontak = magang!!.kontak
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Edit Postingan Magang",
                onBackClick = {navController.navigate("list-magang-my-post")}
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            if (magang != null) {
                item {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Perusahaan",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        TextField(
                            value = perusahaan,
                            onValueChange = { perusahaan = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Masukkan nama perusahaan") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Judul Magang",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = judulMagang,
                            onValueChange = { judulMagang = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Tambahkan judul magang") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Alamat",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = alamat,
                            onValueChange = { alamat = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Tambahkan alamat") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Posisi Magang",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = posisiMagang,
                            onValueChange = { posisiMagang = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Tambahkan posisi magang") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Kualifikasi",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = kualifikasi,
                            onValueChange = { kualifikasi = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Masukkan kualifikasi") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Jenis Magang",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = jenisMagang,
                            onValueChange = { jenisMagang = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Masukkan jenis magang") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Durasi Magang",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = durasiMagang,
                            onValueChange = { durasiMagang = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Tambahkan judul magang") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Deskripsi",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = deskripsiMagang,
                            onValueChange = { deskripsiMagang = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Masukkan deskripsi") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Kontak",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        TextField(
                            value = kontak,
                            onValueChange = { kontak = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .padding(4.dp),
                            placeholder = { Text("Tambahkan kontak") },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Upload File",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                                .clickable {
                                    imagePickerLauncher.launch("image/*")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageUri != null) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = imageUri),
                                    contentDescription = "Selected Image",
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else if (!magang?.gambarMagang.isNullOrEmpty()) {
                                val imageUrl = baseUrl + magang!!.gambarMagang
                                Image(
                                    painter = rememberAsyncImagePainter(model = imageUrl),
                                    contentDescription = "Gambar",
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
                                viewModel.updateMagang(
                                    context = context,
                                    id = magangId,
                                    perusahaan = perusahaan,
                                    judulMagang = judulMagang,
                                    alamat = alamat,
                                    posisiMagang = posisiMagang,
                                    kualifikasi = kualifikasi,
                                    jenisMagang = jenisMagang,
                                    durasiMagang = durasiMagang,
                                    deskripsiMagang = deskripsiMagang,
                                    kontak = kontak,
                                    imageUri = imageUri,
                                    onSuccess = {
                                        Toast.makeText(context, "Magang berhasil dikirim", Toast.LENGTH_SHORT).show()
                                        navController.navigate("list-magang-my-post")
                                    },
                                    onError = { errorMessage ->
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    }
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.button_blue))
                        ) {
                            Text(
                                text = "Post",
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditMagangScreenPrev() {
//    EditMagangScreen()
}
