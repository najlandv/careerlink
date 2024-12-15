//package com.example.careerlink.frontend.component
//import LocationSearchViewModel
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.material3.*
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.viewmodel.compose.viewModel
//import android.content.Intent
//import android.net.Uri
//import androidx.compose.ui.platform.LocalContext
//import com.example.careerlink.services.LocationResult
//import com.example.careerlink.services.MapStreet
//
//@Composable
//fun LocationSearchBar(viewModel: LocationSearchViewModel) {
//    val context = LocalContext.current
//    var searchText by remember { mutableStateOf(TextFieldValue("")) }
//    val locations by viewModel.locations.collectAsState()
//    val selectedLocations = remember { mutableStateListOf<LocationResult>() }
//
//
//    Column  (
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        // Search bar input
//        BasicTextField(
//            value = searchText,
//            onValueChange = {
//                searchText = it
//                if (it.text.isNotBlank()) {
//                    viewModel.searchLocation(it.text)
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
//                .padding(8.dp)
//        )
//
//        // Suggestions list
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .verticalScroll(rememberScrollState())
//        ) {
//            locations.forEach { location ->
//                Text(
//                    text = location.display_name,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                        .clickable {
//                            // Update search bar and add to selected list
//                            searchText = TextFieldValue(location.display_name)
//                            if (!selectedLocations.contains(location)) {
//                                selectedLocations.add(location)
//                            }
//                        }
//                )
//            }
//        }
//
//        // Selected locations list
////        Text(
////            text = "Selected Locations:",
////            style = MaterialTheme.typography.titleMedium,
////            modifier = Modifier.padding(vertical = 16.dp)
////        )
////        Column(
////            modifier = Modifier
////                .fillMaxWidth()
////                .verticalScroll(rememberScrollState())
////        ) {
////            selectedLocations.forEach { selected ->
////                Text(
////                    text = selected.display_name,
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(8.dp)
////                        .clickable {
////                            // Redirect to Google Maps
////                            val uri = Uri.parse("geo:${selected.lat},${selected.lon}?q=${selected.display_name}")
////                            val intent = Intent(Intent.ACTION_VIEW, uri)
////                            intent.setPackage("com.google.android.apps.maps")
////                            context.startActivity(intent)
////                        }
////                )
////            }
////        }
//    }
//}
//
//
//
//@Preview(showBackground = true)
//@Composable
//private fun LocationSearchBarPrev() {
//    val viewModel: LocationSearchViewModel = viewModel()
//    LocationSearchBar(viewModel)
//}


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
fun LocationSearchBar(viewModel: LocationSearchViewModel) {
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
                            }
                        )
                    }
                } else {
                    DropdownMenuItem(
                        text = { Text("No results found") },
                        onClick = {}
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

//        // Selected Location Display
//        if (searchText.text.isNotEmpty()) {
//            Text(
//                text = "Selected Location: ${searchText.text}",
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(vertical = 8.dp)
//            )
//        }
    }
}


@Composable
fun SelectedLocationItem(location: LocationResult, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.1f))
    ) {
        Text(
            text = location.display_name,
            modifier = Modifier.weight(1f).padding(8.dp)
        )
        Button(onClick = onClick) {
            Text("Open in Maps")
        }
    }
}

fun openGoogleMaps(context: android.content.Context, location: LocationResult) {
    val uri = Uri.parse("geo:${location.lat},${location.lon}?q=${location.display_name}")
    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.google.android.apps.maps")
    }
    context.startActivity(intent)
}
//
//@Preview(showBackground = true)
//@Composable
//fun LocationSearchBarPreview() {
//    val mockViewModel = object : LocationSearchViewModel {
//        override val locations = mutableStateOf(
//            listOf(
//                LocationResult("Location A", "12.34", "56.78"),
//                LocationResult("Location B", "98.76", "54.32")
//            )
//        )
//
//        override fun searchLocation(query: String) {
//            // No operation for preview
//        }
//    }
//    LocationSearchBar(viewModel = mockViewModel)
//}

@Preview(showBackground = true)
@Composable
private fun LocationSearchBarPrev() {
    val viewModel: LocationSearchViewModel = viewModel()
    LocationSearchBar(viewModel)
}

