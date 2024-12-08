package com.example.store.features.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    val uiState = cartRepository.getCartProductsStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val cartTotal = cartRepository.getCartTotalStream()
        .onEach { total ->
            if (total == 0.0) {
                viewModelScope.launch {
                    orderRepository.resetOrder()
                }
            } else {
                viewModelScope.launch {
                    orderRepository.updateCartTotal(total)
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0.0
        )

    fun removeProductFromCart(productId: String) {
        viewModelScope.launch {
            try {
                cartRepository.removeCartProduct(productId)
            } catch (e: Exception) {
                Log.d("CartViewModel", "removeProductFromCart: $e")
            }
        }
    }

    fun updateQuantity(productId: String, quantity: Int) {
        viewModelScope.launch {
            try {
                cartRepository.updateQuantity(productId, quantity)
            } catch (e: Exception) {
                Log.d("CartViewModel", "updateQuantity: $e")
            }
        }
    }
}
