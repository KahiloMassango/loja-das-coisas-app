package com.example.store.presentation.screens.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.ProductRepositoryImpl
import com.example.store.core.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val productRepository = ProductRepositoryImpl()
    private val productId: String = checkNotNull(savedStateHandle["productId"])
    private val _uiState = MutableStateFlow<ProductDetailState>(ProductDetailState.Loading)
    val uiState: StateFlow<ProductDetailState> = _uiState

    init {
        viewModelScope.launch {
            try {
                val product = productRepository.getProductById(productId)
                _uiState.value = ProductDetailState.Success(
                    product,
                    product.availableSizes[0],
                    product.availableColors[0]
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = ProductDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateSize(size: String) {
        _uiState.value = (uiState.value as ProductDetailState.Success).copy(size = size)
    }

    fun updateColor(color: String){
        _uiState.value = (uiState.value as ProductDetailState.Success).copy(color = color)
    }

    fun addFavorite() {
        /* TODO */
    }

    fun addCart()  {
        /* TODO */
    }

}

sealed class ProductDetailState {
    object Loading : ProductDetailState()
    data class Error(val message: String) : ProductDetailState()
    data class Success(val product: Product, val size: String, val color: String) : ProductDetailState()
}