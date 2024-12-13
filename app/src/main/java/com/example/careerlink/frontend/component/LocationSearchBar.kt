package com.example.careerlink.frontend.component
import LocationSearchViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import com.example.careerlink.services.LocationResult
import com.example.careerlink.services.MapStreet

@Composable
fun LocationSearchBar(viewModel: LocationSearchViewModel) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val locations by viewModel.locations.collectAsState()
    val selectedLocations = remember { mutableStateListOf<LocationResult>() }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Search bar input
        BasicTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                if (it.text.isNotBlank()) {
                    viewModel.searchLocation(it.text)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                .padding(8.dp)
        )

        // Suggestions list
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            locations.forEach { location ->
                Text(
                    text = location.display_name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            // Update search bar and add to selected list
                            searchText = TextFieldValue(location.display_name)
                            if (!selectedLocations.contains(location)) {
                                selectedLocations.add(location)
                            }
                        }
                )
            }
        }

        // Selected locations list
        Text(
            text = "Selected Locations:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            selectedLocations.forEach { selected ->
                Text(
                    text = selected.display_name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            // Redirect to Google Maps
                            val uri = Uri.parse("geo:${selected.lat},${selected.lon}?q=${selected.display_name}")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.setPackage("com.google.android.apps.maps")
                            context.startActivity(intent)
                        }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun LocationSearchBarPrev() {
    val viewModel: LocationSearchViewModel = viewModel()
    LocationSearchBar(viewModel)
}

