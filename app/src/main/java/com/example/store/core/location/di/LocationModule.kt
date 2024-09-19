package com.example.store.core.location.di

import android.content.Context
import android.location.Geocoder
import com.example.store.core.location.DistanceMatrixApiService
import com.example.store.core.location.GeocodeApiService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    fun providesGeocoder(
        @ApplicationContext context: Context
    ): Geocoder = Geocoder(context)

    @Provides
    fun providesLocationService(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @Provides
    @Singleton
    fun providesGeocodeApiService(): GeocodeApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://nominatim.openstreetmap.org/")
            .build()
            .create(GeocodeApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDistanceMatrixApiService(): DistanceMatrixApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.distancematrix.ai//")
            .build()
            .create(DistanceMatrixApiService::class.java)
    }
}