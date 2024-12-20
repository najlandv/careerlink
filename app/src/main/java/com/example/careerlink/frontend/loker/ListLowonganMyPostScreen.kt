package com.example.careerlink.frontend.loker

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careerlink.R
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.CardAction
import com.example.careerlink.frontend.component.MainTopBar
import com.example.careerlink.frontend.component.MenuMyPost
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.LokerViewModel

@Composable
fun ListLowonganMyPostScreen(
    viewModel: LokerViewModel = hiltViewModel(),
    navController: NavController
) {
    val myPostLokerList by viewModel.myPostLokerList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchMyPostLokerList()
    }

    Scaffold(
        topBar = {
            Column {
                MainTopBar()
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                MenuMyPost("Loker", navController)
            }
        },
        bottomBar = { BottomBar(navController = navController)},
        floatingActionButton = { Button(modifier = Modifier,
            onClick = {
                navController.navigate("add-loker")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_blue),
                contentColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(24.dp),){ Icon(Icons.Default.Add,"add")}}
    ) {
        paddingValues ->
        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage!!,
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
        } else if (myPostLokerList.isEmpty()) {
            Text(
                text = "Tidak ada data loker tersedia.",
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                }
                items(myPostLokerList ) { loker ->
                    CardAction (
                        title = loker.judul_loker,
                        subtitle = "${loker.perusahaan} - ${loker.alamat}",
                        desk = loker.deskripsi_loker,
                        date = loker.tanggal_posting,
                        onEdit = {
                            navController.navigate("edit-loker/${loker.id_loker}")
                            println("Edit id: " + loker.id_loker)
                        },
                        onDeleteConfirmed = {
                            println("onDeleteConfirmed terpanggil")
                            viewModel.deleteLoker(
                                id = loker.id_loker, // ID loker yang ingin dihapus
                                onSuccess = {
                                    Toast.makeText(
                                        // Sesuaikan dengan konteks atau activity Anda
                                        context,
                                        "Loker berhasil dihapus",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onError = { error ->
                                    Toast.makeText(
                                        context,
                                        "Gagal menghapus loker: $error",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListLowonganMyPostPrev() {
//    ListLowonganMyPostScreen()
}