package com.example.careerlink.frontend.loker

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.careerlink.R
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.LokerViewModel

@Composable
fun EditLokerScreen(modifier: Modifier = Modifier, lokerId: Int, viewModel: LokerViewModel = hiltViewModel()) {
    val loker by viewModel.lokerDetail.collectAsState()

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

    LaunchedEffect(lokerId) {
        viewModel.getLokerById(lokerId)
    }

    LaunchedEffect(loker) {
        if (loker != null) {
            perusahaan = loker!!.perusahaan ?: ""
            judulLoker = loker!!.judul_loker ?: ""
            alamat = loker!!.alamat ?: ""
            posisiLoker = loker!!.posisi_loker ?: ""
            kualifikasi = loker!!.kualifikasi ?: ""
            jenisLoker = loker!!.jenis_loker ?: ""
            deskripsiLoker = loker!!.deskripsi_loker ?: ""
            kontak = loker!!.kontak ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopBar("Edit Postingan Lowongan Kerja")
        },
        bottomBar = {
            BottomBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            if (loker != null) {
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
                            value = loker?.perusahaan ?: "",
                            onValueChange = {  },
                            placeholder = { "Judul" },
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFFFC107),
                                unfocusedBorderColor = Color(0xFFFFC107)
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Deskripsi",

                            style = TextStyle(
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        OutlinedTextField(
                            value = "Deskripsi Lowongan",
                            onValueChange = {  },
                            placeholder = { "Deskripsi Lowongan" },
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
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(R.color.button_blue))
                        ) {
                            Text(
                                text = "Simpan",
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

@Preview
@Composable
private fun EditLowonganPrev() {
//    EditLokerScreen()
}