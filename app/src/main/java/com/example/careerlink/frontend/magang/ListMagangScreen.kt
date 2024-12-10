package com.example.careerlink.frontend.magang

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import com.example.careerlink.frontend.component.CardList
import com.example.careerlink.frontend.component.TopBar

data class InternshipData(
    val title: String,
    val description: String,
    val date: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListMagangScreen(
    modifier: Modifier = Modifier
) {
    val magangList = listOf(
        InternshipData(
            title = "GrowMentor",
            description = "Pengembang Perangkat Lunak (Software Developer Intern)",
            date = "2024-02-03"
        ),
        InternshipData(
            title = "PT Telkom Indonesia",
            description = "Pengembang Perangkat Lunak (Software Developer Intern)",
            date = "2024-02-03"
        ),
        InternshipData(
            title = "PT Angkasa Pura",
            description = "Pengembang Perangkat Lunak (Software Developer Intern)",
            date = "2024-02-03"
        ),
        InternshipData(
            title = "PT Angkasa Pura",
            description = "Pengembang Perangkat Lunak (Software Developer Intern)",
            date = "2024-02-03"
        ),
    )

    Scaffold(
        topBar = {
            TopBar(
                text = "Magang",
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(magangList) { magang ->
                        CardList(
                            title = magang.title,
                            subtitle = "Posisi Magang:",
                            desk = magang.description,
                            date = magang.date
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ListMagangScreenPrev() {
    ListMagangScreen()
}
