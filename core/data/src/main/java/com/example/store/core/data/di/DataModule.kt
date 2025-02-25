package com.example.store.core.data.di

import android.content.Context
import android.location.Geocoder
import com.example.store.core.data.repository.AddressRepository
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.DefaultAddressRepository
import com.example.store.core.data.repository.DefaultCartRepository
import com.example.store.core.data.repository.DefaultFavoriteRepository
import com.example.store.core.data.repository.DefaultLocationRepository
import com.example.store.core.data.repository.DefaultProductRepository
import com.example.store.core.data.repository.DefaultRecentSearchRepository
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.LocationRepository
import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.data.repository.RecentSearchRepository
import com.example.store.core.data.repository.StoreRepository
import com.example.store.core.data.repository.StoreRepositoryImpl
import com.example.store.core.data.util.ConnectivityManagerNetworkMonitor
import com.example.store.core.data.util.NetworkMonitor
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.RecentSearchDao
import com.example.store.core.network.datasources.ProductNetworkDatasource
import com.example.store.core.network.datasources.StoreNetworkDatasource
import com.example.store.core.network.retrofit.DistanceApiService
import com.example.store.core.network.retrofit.GeocodeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesFavoriteRepository(
        favoritesDao: FavoritesDao
    ): FavoriteRepository = DefaultFavoriteRepository(favoritesDao)


    @Provides
    fun providesStoreRepository(
        datasource: StoreNetworkDatasource
    ): StoreRepository = StoreRepositoryImpl(datasource)

    @Provides
    fun providesAddressesRepository(
        addressesDao: AddressesDao
    ): AddressRepository = DefaultAddressRepository(addressesDao)

    @Provides
    fun providesCartRepository(
        cartDao: CartDao
    ): CartRepository = DefaultCartRepository(cartDao)


    @Provides
    fun providesRecentSearchRepository(
        recentSearchDao: RecentSearchDao
    ): RecentSearchRepository = DefaultRecentSearchRepository(recentSearchDao)

    @Provides
    fun providesProductRepository(
        networkDatasource: ProductNetworkDatasource
    ): ProductRepository = DefaultProductRepository(networkDatasource)

    @Provides
    fun providesLocationRepository(
        geocodeApiService: GeocodeApiService,
        geocodeService: Geocoder,
        distanceApiService: DistanceApiService
    ): LocationRepository =
        DefaultLocationRepository(
            geocodeApiService,
            geocodeService,
            distanceApiService
        )

    @Provides
    fun providesNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor = ConnectivityManagerNetworkMonitor(
        context
    )


}