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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
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
import com.example.careerlink.services.NotificationService
import com.example.careerlink.viewmodels.SertifikasiViewModel

@Composable
fun TambahSertifikasiScreen(
    modifier: Modifier = Modifier,
    viewModel: SertifikasiViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    navController: NavController
) {
    var instansi by remember { mutableStateOf("") }
    var judulSertifikasi by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var tanggalPelaksanaan by remember { mutableStateOf("") }
    var kontak by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Tambahkan Info Sertifikasi",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Instansi",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = instansi,
                    onValueChange = { instansi = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
            }

            item {
                Text(
                    text = "Judul Sertifikasi",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = judulSertifikasi,
                    onValueChange = { judulSertifikasi = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
            }

            item {
                Text(
                    text = "Deskripsi",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = deskripsi,
                    onValueChange = { deskripsi = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
            }

            item {
                Text(
                    text = "Tanggal Pelaksanaan",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = tanggalPelaksanaan,
                    onValueChange = { tanggalPelaksanaan = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
            }

            item {
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
                        .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
            }

            item {
                Text(
                    text = "Harga",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = harga,
                    onValueChange = { harga = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
            }

            item {
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
            }

            item {
                Button(
                    onClick = {
                        viewModel.postSertifikasi(
                            context = context,
                            instansi = instansi,
                            judulSertifiksai = judulSertifikasi,
                            deskripsi = deskripsi,
                            tanggalPelaksanaan = tanggalPelaksanaan,
                            kontak = kontak,
                            harga = harga,
                            imageUri = imageUri,

                            onSucces = {
                                // Mengirim notifikasi
                                NotificationService.sendNotification(
                                    context,
                                    "Sertifikasi Baru",
                                    "Sertifikasi baru berhasil ditambahkan: $judulSertifikasi"
                                )

                                // Menampilkan toast sebagai umpan balik
                                Toast.makeText(context, "Sertifikasi berhasil dikirim", Toast.LENGTH_SHORT).show()

                                // Navigasi ke halaman list-sertifikasi-my-post
                                navController.navigate("list-sertifikasi-my-post") {
                                    popUpTo("list-sertifikasi-my-post") {
                                        inclusive = true
                                    }
                                }
                            },
                            onError = {errorMessage ->
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
                        text = "Post",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TambahSertifikasiScreenPrev() {
//    TambahSertifikasiScreen()
}
