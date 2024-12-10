package com.example.careerlink.frontend.magang

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import com.example.careerlink.frontend.component.ButtonAction
import com.example.careerlink.frontend.component.CardAction
import com.example.careerlink.frontend.component.MenuMyPost
import com.example.careerlink.frontend.component.TopBar

data class MagangData(
    val title: String,
    val description: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPostMagangSayaScreen(
    modifier: Modifier = Modifier,
    onEditMagang: (MagangData) -> Unit
) {
    var magangList by remember {
        mutableStateOf(
            listOf(
                MagangData(
                    title = "Software Engineer Intern",
                    description = "Magang pengembangan perangkat lunak di perusahaan teknologi.",
                    date = "2024-01-15"
                ),
                MagangData(
                    title = "Data Analyst Intern",
                    description = "Magang analisis data di startup fintech.",
                    date = "2023-12-01"
                ),
                MagangData(
                    title = "UI/UX Designer Intern",
                    description = "Magang desain antarmuka pengguna di studio desain.",
                    date = "2023-11-10"
                )
            )
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Postingan Magang Saya",
                onBackClick = { }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                MenuMyPost()

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(magangList) { magang ->
                        CardAction(
                            title = magang.title,
                            subtitle = "Deskripsi:",
                            desk = magang.description,
                            date = magang.date,
                            onEdit = { onEditMagang(magang) },
                            onDeleteConfirmed = {
                                magangList = magangList.toMutableList().also { it.remove(magang) }
                            }
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ListPostMagangSayaScreenPrev() {
    ListPostMagangSayaScreen(onEditMagang = {})
}
