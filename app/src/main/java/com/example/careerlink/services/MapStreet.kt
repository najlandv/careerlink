package com.example.careerlink.services
import retrofit2.http.GET
import retrofit2.http.Query

interface MapStreet {
    abstract val lon: String
    abstract val lat: String
    abstract val display_name: Any

    @GET("search")
    suspend fun searchLocations(
        @Query("q") query: String,
        @Query("format") format: String = "json"
    ): List<LocationResult>
}

data class LocationResult(
    val display_name: String,
    val lat: String,
    val lon: String
)

