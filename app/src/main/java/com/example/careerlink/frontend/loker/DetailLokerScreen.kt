package com.example.careerlink.frontend.loker

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.CardDetail
import com.example.careerlink.frontend.component.TopBar

@Composable
fun DetailLokerScreen(modifier: Modifier = Modifier) {
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
            item {
                CardDetail()

            }

        }
    }
}

@Preview
@Composable
private fun DetailLokerPrev() {
    DetailLokerScreen()
}