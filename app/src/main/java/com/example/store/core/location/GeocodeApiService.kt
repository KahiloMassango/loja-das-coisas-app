package com.example.store.core.location

import com.example.store.core.location.model.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodeApiService {

    @GET("search.php?countrycodes=ao&format=jsonv2")
    suspend fun getLocationsByName(
        @Query("q") location: String): List<LocationResponse>

}