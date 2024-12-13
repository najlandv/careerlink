import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careerlink.R
import com.example.careerlink.frontend.component.TopBar

@Composable
fun TambahSertifikasiScreen(modifier: Modifier = Modifier) {
    val titleState = remember { mutableStateOf(TextFieldValue()) }
    val infoState = remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        topBar = {
            TopBar(
                text = "Tambahkan Info Sertifikasi",
                onBackClick = { /* Handle back navigation */ }
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
                    text = "Judul",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = titleState.value,
                    onValueChange = { titleState.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Yellow, RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
            }

            item {
                Text(
                    text = "Informasi Sertifikasi",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = infoState.value,
                    onValueChange = { infoState.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
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
                        .clickable { /* Handle file upload */ },
                    contentAlignment = Alignment.Center
                ) {
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

            item {
                Button(
                    onClick = { /* Handle post action */ },
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
    TambahSertifikasiScreen()
}
