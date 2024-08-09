package com.example.store.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.store.core.database.dao.FavoriteProductsDao
import com.example.store.core.database.model.FavoriteProductEntity

@Database(version = 1, entities = [FavoriteProductEntity::class], exportSchema = false)
abstract class StoreDatabase: RoomDatabase() {

    abstract fun favoriteProductsDao(): FavoriteProductsDao

    companion object {
        @Volatile
        private var instance: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, StoreDatabase::class.java, "store_database")
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }


}