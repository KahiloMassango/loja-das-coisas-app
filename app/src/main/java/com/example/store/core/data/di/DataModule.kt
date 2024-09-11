package com.example.store.core.data.di

import android.location.Geocoder
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.CartRepositoryImpl
import com.example.store.core.data.repository.DefaultLocationRepository
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.FavoriteRepositoryImpl
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.location.GeocodeApiService
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    fun providesFavoriteRepository(
        favoritesDao: FavoritesDao
    ): FavoriteRepository = FavoriteRepositoryImpl(favoritesDao)

    @Provides
    fun providesCartRepository(
        cartDao: CartDao
    ): CartRepository = CartRepositoryImpl(cartDao)

    @Provides
    fun providesLocationRepository(
        locationApiService: GeocodeApiService,
        geocodeService: Geocoder,
        locationService: FusedLocationProviderClient
    ): LocationRepository =
        DefaultLocationRepository(locationApiService, geocodeService, locationService)


}