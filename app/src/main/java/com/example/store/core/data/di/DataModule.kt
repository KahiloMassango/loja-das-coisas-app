package com.example.store.core.data.di

import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.FavoriteRepositoryImpl
import com.example.store.core.database.dao.FavoriteProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    fun providesFavoriteRepository(
        favoriteProductsDao: FavoriteProductsDao
    ): FavoriteRepository = FavoriteRepositoryImpl(favoriteProductsDao)

}