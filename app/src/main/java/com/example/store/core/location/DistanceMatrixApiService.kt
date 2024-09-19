package com.example.store.core.location

import com.example.store.core.location.model.distance_matrix.DistanceRespone
import retrofit2.http.GET
import retrofit2.http.Query

interface DistanceMatrixApiService {

    @GET("maps/api/distancematrix/json?key=ocxmn1DKn1z5ij2nd21QSPmyVMhKWGAsbmKpKY6eoqUO1pPSOjSjhjgv8iMXqTrB")
    suspend fun getDistanceMatrix(
        @Query("origins") origins: String,
        @Query("destinations") destinations: String,
    ): DistanceRespone
}