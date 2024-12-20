package com.example.careerlink.frontend.magang

import android.widget.Toast
import androidx.compose.foundation.layout.*
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
import com.example.careerlink.frontend.component.MainTopBar
import com.example.careerlink.frontend.component.MenuMyPost
import com.example.careerlink.frontend.component.TopBar
import com.example.careerlink.viewmodels.MagangViewModel



@Composable
fun ListPostMagangSayaScreen(
    modifier: Modifier = Modifier,
    viewModel: MagangViewModel = hiltViewModel(),
    navController: NavController
) {
    val myPostMagangList by viewModel.myPostMagangList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchMyPostMagangList()
    }

    Scaffold(
        topBar = {
            Column {
                MainTopBar()
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                MenuMyPost("Magang", navController)
            }
        },
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = { Button(modifier = Modifier,
            onClick = {
                navController.navigate("add-magang")
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
        } else if (myPostMagangList.isEmpty()) {
            Text(
                text = "Tidak ada data magang tersedia.",
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        } else {

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.padding(paddingValues)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(myPostMagangList) { magang ->
                        CardAction(
                            title = magang.judulMagang,
                            subtitle = "Deskripsi:",
                            desk = magang.deskripsiMagang,
                            date = magang.tanggalPosting,
                            onEdit = {
                                navController.navigate("edit-magang/${magang.idMagang}")
                            },
                            onDeleteConfirmed = {
                                viewModel.deleteMagang(
                                    id = magang.idMagang,
                                    onSucces = {
                                        Toast.makeText(
                                            context,
                                            "Magang berhasil dihapus",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                    onError = { error ->
                                        Toast.makeText(
                                            context,
                                            "Gagal menghapus magang: $error",
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
private fun ListPostMagangSayaScreenPrev() {
//    ListPostMagangSayaScreen(onEditMagang = {})
}
