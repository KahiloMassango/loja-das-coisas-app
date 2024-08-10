package com.example.store.core.data.di

import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.CartRepositoryImpl
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.FavoriteRepositoryImpl
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
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
}