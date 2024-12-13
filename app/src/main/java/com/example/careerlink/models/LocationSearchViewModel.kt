import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careerlink.services.LocationResult
import com.example.careerlink.services.MapStreet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationSearchViewModel : ViewModel() {
    private val api: MapStreet

    private val _locations = MutableStateFlow<List<LocationResult>>(emptyList())
    val locations: StateFlow<List<LocationResult>> = _locations

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nominatim.openstreetmap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(MapStreet::class.java)
    }

    fun searchLocation(query: String) {
        viewModelScope.launch {
            try {
                val results = api.searchLocations(query)
                _locations.value = results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
