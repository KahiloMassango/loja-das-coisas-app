package com.example.store.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.RecentSearchDao
import com.example.store.core.database.dao.UserDao
import com.example.store.core.database.model.AddressEntity
import com.example.store.core.database.model.CartProductEntity
import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.database.model.OrderEntity
import com.example.store.core.database.model.RecentSearchEntity
import com.example.store.core.database.model.UserEntity

@Database(
    version = 2,
    entities = [
        FavoriteProductEntity::class,
        CartProductEntity::class,
        OrderEntity::class,
        AddressEntity::class,
        RecentSearchEntity::class,
        UserEntity::class
    ],
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
    abstract fun cartDao(): CartDao
    abstract fun addressesDao(): AddressesDao
    abstract fun recentSearchDao(): RecentSearchDao
    abstract fun userDao(): UserDao


    companion object {
        @Volatile
        private var instance: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, StoreDatabase::class.java, "store_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}
