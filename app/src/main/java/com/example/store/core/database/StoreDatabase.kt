package com.example.store.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.store.core.database.dao.AddressesDao
import com.example.store.core.database.dao.CartDao
import com.example.store.core.database.dao.FavoritesDao
import com.example.store.core.database.dao.OrderDao
import com.example.store.core.database.model.AddressEntity
import com.example.store.core.database.model.CartProductEntity
import com.example.store.core.database.model.FavoriteProductEntity
import com.example.store.core.database.model.OrderEntity
import com.example.store.core.model.Order
import com.example.store.core.model.asEntity
import java.util.concurrent.Executors

@Database(
    version = 2,
    entities = [
        FavoriteProductEntity::class,
        CartProductEntity::class,
        OrderEntity::class,
        AddressEntity::class
    ],
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
    abstract fun addressesDao(): AddressesDao


    companion object {
        @Volatile
        private var instance: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, StoreDatabase::class.java, "store_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadExecutor().execute {
                                instance?.orderDao()?.addOrder(Order().asEntity())
                            }
                        }
                    })
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }


}