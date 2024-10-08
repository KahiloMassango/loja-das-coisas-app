package com.example.store.core.database.di

import com.example.store.core.database.StoreDatabase
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.OrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesFavoritesDao(
        database: StoreDatabase,
    ): FavoritesDao = database.favoritesDao()

    @Provides
    fun providesCartDao(
        database: StoreDatabase
    ): CartDao  = database.cartDao()

    @Provides
    fun providesOrderDao(
        database: StoreDatabase
    ): OrderDao  = database.orderDao()

    @Provides
    fun providesAddressesDao(
        database: StoreDatabase
    ): AddressesDao  = database.addressesDao()

}