 package com.example.store.feature.product_detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.model.asCartProduct
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.ProductRepositoryImpl
import com.example.store.core.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

 @HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val cartRepository: CartRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val productRepository = ProductRepositoryImpl()
    private val productId: String = checkNotNull(savedStateHandle["productId"])

    var productSize by mutableStateOf("")
        private set

    var productColor by mutableStateOf("")
        private set

    var uiState: StateFlow<ProductDetailState> = flow {
        emit(ProductDetailState.Loading)
        try {
            val product = productRepository.getProductById(productId)
            productSize = product.availableSizes[0]
            productColor = product.availableColors[0]
            emit(ProductDetailState.Success(product))
        } catch (e: Exception) {
            Log.e("ProductDetailViewModel", "Error fetching product", e)
            emit(ProductDetailState.Error)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProductDetailState.Loading
    )


    val isFavorite: StateFlow<Boolean> = favoriteRepository.checkFavoriteProductStream(productId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )



    fun refresh() {
       viewModelScope.launch {
           uiState.collect()
       }
    }

    fun updateSize(size: String) {
        productSize = size
    }

    fun updateColor(color: String) {
        productColor = color
    }

    fun addFavorite() {
        viewModelScope.launch {
            val state = uiState.value as ProductDetailState.Success
            favoriteRepository.addFavoriteProduct(state.product)
        }
    }

    fun addCart() {
        viewModelScope.launch(Dispatchers.IO) {
            val product = (uiState.value as ProductDetailState.Success).product
            cartRepository.addToCart(product.asCartProduct(productColor, productSize))
        }
    }
}


sealed class ProductDetailState {
    data object Loading : ProductDetailState()
    data object Error : ProductDetailState()
    data class Success(val product: Product) : ProductDetailState()
}