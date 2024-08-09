package com.example.store.core.database.di

import com.example.store.core.database.StoreDatabase
import com.example.store.core.database.dao.FavoriteProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesFavoriteProductsDao(
        database: StoreDatabase,
    ): FavoriteProductsDao = database.favoriteProductsDao()


}