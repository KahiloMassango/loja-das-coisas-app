package com.example.store.core.data.di

import android.content.Context
import android.location.Geocoder
import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.data.repository.DefaultAddressRepository
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.DefaultCartRepository
import com.example.store.core.data.repository.DefaultLocationRepository
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.DefaultFavoriteRepository
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.data.repository.OrderRepository
import com.example.store.core.data.repository.DefaultOrderRepository
import com.example.store.core.data.util.ConnectivityManagerNetworkMonitor
import com.example.store.core.data.util.NetworkMonitor
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.OrderDao
import com.example.store.core.location.DistanceMatrixApiService
import com.example.store.core.location.GeocodeApiService
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    fun providesFavoriteRepository(
        favoritesDao: FavoritesDao
    ): FavoriteRepository = DefaultFavoriteRepository(favoritesDao)

    @Provides
    fun providesAddressesRepository(
        addressesDao: AddressesDao
    ): AddressRepository = DefaultAddressRepository(addressesDao)

    @Provides
    fun providesCartRepository(
        cartDao: CartDao
    ): CartRepository = DefaultCartRepository(cartDao)

    @Provides
    fun providesOrderRepository(
        orderDao: OrderDao
    ): OrderRepository = DefaultOrderRepository(orderDao)

    @Provides
    fun providesLocationRepository(
        locationApiService: GeocodeApiService,
        geocodeService: Geocoder,
        distanceMatrixService: DistanceMatrixApiService
    ): LocationRepository =
        DefaultLocationRepository(
            locationApiService,
            geocodeService,
            distanceMatrixService
        )

    @Provides
    fun providesNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor = ConnectivityManagerNetworkMonitor(
        context
    )


}