import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careerlink.R
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.CardAction
import com.example.careerlink.frontend.component.MenuMyPost
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.SertifikasiViewModel

@Composable
fun ListMyPostSertifikasiScreen(
    modifier: Modifier = Modifier,
    viewModel: SertifikasiViewModel = hiltViewModel(),
    navController: NavController
) {
    val myPostSertifikasi by viewModel.myPostSerifikasiList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchMyPostSertifikasiList()
    }


    Scaffold(
        topBar = {
            Column {
                TopBar(
                    text = "Postingan Saya",
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                MenuMyPost("Sertifikasi", navController)
            }
        },
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = { Button(modifier = Modifier,
            onClick = {
                navController.navigate("add-sertfikasi")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_blue),
                contentColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(24.dp),){ Icon(Icons.Default.Add,"add")}
        }
    ) { paddingValues ->

        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage!!,
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
        } else if (myPostSertifikasi.isEmpty()) {
            Text(
                text = "Tidak ada data magang tersedia.",
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(myPostSertifikasi) { sertifikasi ->
                    CardAction(
                        title = sertifikasi.judulSertifikasi,
                        subtitle = "Deskripsi:",
                        desk = sertifikasi.deskripsi,
                        date = sertifikasi.tanggalPosting,
                        onEdit = {
                            navController.navigate("edit-sertifikasi/${sertifikasi.idSertifikasi}")
                        },
                        onDeleteConfirmed = {
                            viewModel.deleteSertifikasi(
                                id = sertifikasi.idSertifikasi,
                                onSucces = {
                                    Toast.makeText(
                                        context,
                                        "Sertifikasi berhasil dihapus",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onError = { error ->
                                    Toast.makeText(
                                        context,
                                        "Gagal menghapus sertifikasi: $error",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        }
                    )
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun ListMyPostSertifikasiScreenPrev() {
//    ListMyPostSertifikasiScreen(onEditSertifikasi = {})
}
