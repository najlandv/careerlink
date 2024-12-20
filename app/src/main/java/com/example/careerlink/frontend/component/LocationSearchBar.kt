package com.example.careerlink.frontend.component

import LocationSearchViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.careerlink.R
import com.example.careerlink.services.LocationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchBar(viewModel: LocationSearchViewModel, onLocationSelected: (LocationResult) -> Unit) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val locations by viewModel.locations.collectAsState()
    val expanded = remember { mutableStateOf(false) } // State for dropdown visibility

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(16.dp)
    ) {
        // Dropdown with ExposedDropdownMenuBox
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    if (it.text.isNotBlank()) {
                        viewModel.searchLocation(it.text)
                        expanded.value = locations.isNotEmpty()
                    } else {
                        expanded.value = false
                    }
                },
                label = { Text("Search location") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFFC107),
                    unfocusedBorderColor = Color(0xFFFFC107)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor() // Ensure the dropdown aligns with the TextField

            )

            // Suggestions Dropdown
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                if (locations.isNotEmpty()) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location.display_name) },
                            onClick = {
                                searchText = TextFieldValue(location.display_name)
                                expanded.value = false
                                onLocationSelected(location)
                            }
                        )
                    }
                } else {
                    DropdownMenuItem(
                        text = { Text("No results found") },
                        onClick = {

                        }
                    )
                }
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
private fun LocationSearchBarPrev() {
    val viewModel: LocationSearchViewModel = viewModel()
//    LocationSearchBar(viewModel)
}

