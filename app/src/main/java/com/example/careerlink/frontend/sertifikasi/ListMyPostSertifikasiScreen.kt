import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import com.example.careerlink.frontend.component.CardAction
import com.example.careerlink.frontend.component.MenuMyPost
import com.example.careerlink.frontend.component.TopBar

data class SertifikasiData(
    val title: String,
    val description: String,
    val date: String
)

@Composable
fun ListMyPostSertifikasiScreen(
    modifier: Modifier = Modifier,
    onEditSertifikasi: (SertifikasiData) -> Unit
) {
    var sertifikasiList by remember {
        mutableStateOf(
            listOf(
                SertifikasiData(
                    title = "Certified Kotlin Developer",
                    description = "Sertifikasi terkait penguasaan bahasa Kotlin.",
                    date = "2024-02-03"
                ),
                SertifikasiData(
                    title = "Professional Android Developer",
                    description = "Sertifikasi pengembangan aplikasi Android.",
                    date = "2023-12-15"
                ),
                SertifikasiData(
                    title = "Cloud Computing Specialist",
                    description = "Sertifikasi penggunaan layanan cloud.",
                    date = "2023-11-20"
                )
            )
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Postingan Saya",
            )
        }
    ) { paddingValues ->
        Spacer(modifier = Modifier.height(16.dp))

        MenuMyPost()

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sertifikasiList) { sertifikasi ->
                CardAction(
                    title = sertifikasi.title,
                    subtitle = "Deskripsi:",
                    desk = sertifikasi.description,
                    date = sertifikasi.date,
                    onEdit = { onEditSertifikasi(sertifikasi) },
                    onDeleteConfirmed = {
                        sertifikasiList = sertifikasiList.toMutableList().apply {
                            remove(sertifikasi)
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListMyPostSertifikasiScreenPrev() {
    ListMyPostSertifikasiScreen(onEditSertifikasi = {})
}
