package com.example.store.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.store.core.database.model.OrderEntity
import com.example.store.core.model.cart.DeliveryMethod
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert
    fun addOrder(orderEntity: OrderEntity)

    @Query("SELECT * FROM orders WHERE id = 0")
    fun getOrder(): OrderEntity

    @Query("SELECT * FROM orders WHERE id = 0")
    fun getOrderStream(): Flow<OrderEntity>

    @Query(
        "UPDATE orders SET cartTotal = 0.0" +
                ", deliveryFee = 0.0, orderTotal = 0.0, " +
            " paymentType = 'Cash', deliveryMethod = 'DELIVERY' WHERE id = 0")
    suspend fun resetOrder()

    @Query("UPDATE orders SET deliveryLocationName = :locationName, " +
            "latitude = :latitude, longitude = :longitude WHERE id = 0")
    suspend fun changeDeliveryLocation(locationName: String, latitude: Double, longitude: Double)

    @Query("UPDATE orders SET paymentType = :paymentType WHERE id = 0")
    suspend fun updatePaymentType(paymentType: String)

    @Query("UPDATE orders SET deliveryMethod = :deliveryMethod WHERE id = 0")
    suspend fun updateDeliveryMethod(deliveryMethod: DeliveryMethod)

    @Query("UPDATE orders SET deliveryFee = :deliveryFee WHERE id = 0")
    suspend fun updateDeliveryFee(deliveryFee: Double)

    @Query("UPDATE orders SET cartTotal = :ordersTotal WHERE id = 0")
    suspend fun updateCartTotal(ordersTotal: Double)

    @Query("UPDATE orders SET orderTotal = :checkoutTotal WHERE id = 0")
    suspend fun updateOrderTotal(checkoutTotal: Double)
}