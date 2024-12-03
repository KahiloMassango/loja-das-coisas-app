package com.example.store.feature.shop

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.model.product.Product
import com.example.store.feature.shop.model.OrderCriteria
import com.example.store.feature.shop.model.getFilters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val category = savedStateHandle.get<String>("category") ?: ""
    val subcategory = savedStateHandle.get<String>("subcategory") ?: ""


    private val _uiState = MutableStateFlow<ShopUiState>(ShopUiState.Loading)
    val uiState = _uiState.asStateFlow()

    val filters = getFilters(category, subcategory)

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            //delay(3000L)
            try {
                val products = productRepository.getProducts(category, subcategory)
                _uiState.value = ShopUiState.Success(products)
            } catch (e: Exception) {
                _uiState.value = ShopUiState.Error
                Log.d("ShopViewModel", "getProducts: $e")
            }
        }

    }

    fun updateOrderOption(orderBy: String) {
        _uiState.update { currentState ->
            (currentState as ShopUiState.Success).copy(orderBy = orderBy)
        }
    }

    fun updateFilter(filter: String) {
        _uiState.update { currentState ->
            (currentState as ShopUiState.Success).copy(filter = filter)
        }
    }

}

sealed class ShopUiState {
    data object Loading : ShopUiState()
    data class Success(
        val products: List<Product>,
        val orderBy: String = OrderCriteria.Popular.title,
        val filter: String = ""
    ) : ShopUiState()

    data object Error : ShopUiState()

}