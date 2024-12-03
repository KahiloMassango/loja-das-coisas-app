package com.example.store.core.network.di

import android.content.Context
import android.location.Geocoder
import com.example.store.core.network.retrofit.DistanceApiService
import com.example.store.core.network.retrofit.GeocodeApiService
import com.example.store.core.network.retrofit.StoreApiService
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
object NetworkModule {

    @Provides
    fun provideStoreApiService(): StoreApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:8080/")
            .build()
            .create(StoreApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideDistanceApiService(): DistanceApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.distancematrix.ai/")
            .build()
            .create(DistanceApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideGeocodeApiService(): GeocodeApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://nominatim.openstreetmap.org/")
            .build()
            .create(GeocodeApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideGeocoderService(
        @ApplicationContext context: Context
    ): Geocoder  = Geocoder(context)
}