package com.example.store.core.network.di

import com.example.store.core.network.ProductNetworkDatasourceImp
import com.example.store.core.network.StoreNetworkDatasourceImpl
import com.example.store.core.network.datasources.ProductNetworkDatasource
import com.example.store.core.network.datasources.StoreNetworkDatasource
import com.example.store.core.network.retrofit.AppApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    fun providesProductNetworkDatasource(
        apiService: AppApiService
    ): ProductNetworkDatasource = ProductNetworkDatasourceImp(apiService)

    @Provides
    fun providesStoreNetworkDatasource(
        apiService: AppApiService
    ): StoreNetworkDatasource = StoreNetworkDatasourceImpl(apiService)

}