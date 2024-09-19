package com.example.store.core.data.di

import android.location.Geocoder
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.CartRepositoryImpl
import com.example.store.core.data.repository.LocationRepositoryImpl
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.FavoriteRepositoryImpl
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.data.repository.OrderRepositoryImpl
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.OrderDao
import com.example.store.core.location.DistanceMatrixApiService
import com.example.store.core.location.GeocodeApiService
import com.example.store.core.model.Order
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
    fun providesOrderRepository(
        orderDao: OrderDao
    ): OrderRepository = OrderRepositoryImpl(orderDao)

    @Provides
    fun providesLocationRepository(
        locationApiService: GeocodeApiService,
        geocodeService: Geocoder,
        locationService: FusedLocationProviderClient,
        distanceMatrixService: DistanceMatrixApiService
    ): LocationRepository =
        LocationRepositoryImpl(
            locationApiService,
            geocodeService,
            locationService,
            distanceMatrixService
        )


}