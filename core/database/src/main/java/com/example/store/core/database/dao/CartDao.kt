package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.store.core.database.model.CartProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartProduct(cartProductEntity: CartProductEntity)

    @Query("DELETE FROM cart WHERE  productItemId = :id")
    suspend fun deleteCartProduct(id: String)

    @Query("UPDATE cart SET quantity = :quantity WHERE productItemId = :id")
    suspend fun updateQuantity(id: String, quantity: Int)

    @Query("SELECT * FROM cart WHERE productItemId == :id")
    suspend fun getProductByID(id: String): CartProductEntity?

    @Query("SELECT SUM(price * quantity) FROM cart ")
    fun getCartTotalStream(): Flow<Double>

    @Query("SELECT COUNT(*) FROM cart")
    fun getCartProductCount(): Flow<Int>

    @Query("SELECT * FROM cart")
    fun getCartProducts(): Flow<List<CartProductEntity>>

}