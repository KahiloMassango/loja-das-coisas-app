package com.example.store.feature.product_detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.P1V
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.data.repository.ProductRepository
import com.example.store.core.model.product.ProductItem
import com.example.store.core.model.product.ProductWithVariation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val favoriteRepository: FavoriteRepository,
    private val cartRepository: CartRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val productId = savedStateHandle.get<String>("productId") !!

    private val _selectedColor = MutableStateFlow<String?>(null)
    val selectedColor: StateFlow<String?> = _selectedColor

    private val _selectedSize = MutableStateFlow<String?>(null)
    val selectedSize: StateFlow<String?> = _selectedSize

    private val _filteredColors = MutableStateFlow<List<String>>(emptyList())
    val filteredColors: StateFlow<List<String>> = _filteredColors

    private val _filteredSizes = MutableStateFlow<List<String>>(emptyList())
    val filteredSizes: StateFlow<List<String>> = _filteredSizes

    private val _selectedItem = MutableStateFlow<ProductItem?>(null)
    val selectedItem: StateFlow<ProductItem?> = _selectedItem

    val productImages = mutableStateOf<List<String>>(emptyList())

    var uiState: StateFlow<ProductDetailState> = flow {
        emit(ProductDetailState.Loading)
        try {
            val product = P1V
            emit(ProductDetailState.Success(product))
            updateAttributes(product)
        } catch (e: Exception) {
            Log.e("ProductDetailViewModel", "Error fetching product", e)
            emit(ProductDetailState.Error)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProductDetailState.Loading
    )


    fun updateAttributes(product: ProductWithVariation) {
        productImages.value = product.productItems.map { it.image }.filterNotNull() + product.image
        when (product.subCategory) {
            "clothes", "shoes" -> {
                _filteredColors.value = product.productItems.mapNotNull { it.color }.distinct()
                selectColor(_filteredColors.value.firstOrNull())
            }

            "accessories" -> {
                _filteredColors.value = product.productItems.mapNotNull { it.color }.distinct()
                selectColor(_filteredColors.value.firstOrNull())
                _filteredSizes.value = emptyList()
            }

            "skincare", "bodycare", "fragrance" -> {
                _filteredColors.value = emptyList()
                _filteredSizes.value = product.productItems.mapNotNull { it.size }.distinct()
                selectSize(_filteredSizes.value.firstOrNull())
            }
        }
    }


    fun selectColor(color: String?) {
        _selectedColor.value = color
        val productItems = (uiState.value as ProductDetailState.Success).product.productItems

        // Update sizes based on the selected color
        val sizesForColor = productItems
            .filter { it.color == color }
            .mapNotNull { it.size }
            .distinct()
        _filteredSizes.value = sizesForColor

        // Automatically select the first available size
        _selectedSize.value = sizesForColor.firstOrNull()

        updateSelectedItem(productItems)
    }

    fun selectSize(size: String?) {
        _selectedSize.value = size
        val productItems = (uiState.value as ProductDetailState.Success).product.productItems
        updateSelectedItem(productItems)
    }

    private fun updateSelectedItem(productItems: List<ProductItem>) {
        _selectedItem.value = productItems.firstOrNull {
            it.color == _selectedColor.value && it.size == _selectedSize.value
        }
    }


    val isFavorite: StateFlow<Boolean> =
        favoriteRepository.checkFavoriteProductStream(productId.toString())
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


    fun addFavorite() {
        viewModelScope.launch {
            try {
                val state = uiState.value as ProductDetailState.Success
                //favoriteRepository.addFavoriteProduct(state.product)
            } catch (e: Exception) {
                Log.d("ProductDetailViewModel", "addFavoriteProduct: $e")
            }

        }
    }

    fun addCart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val product = (uiState.value as ProductDetailState.Success).product
                //cartRepository.addToCart(product.asCartProduct(productColor, productSize))
            } catch (e: Exception) {
                Log.d("ProductDetailViewModel", "addToCart: $e")
            }

        }
    }
}


sealed class ProductDetailState {
    data object Loading : ProductDetailState()
    data object Error : ProductDetailState()
    data class Success(val product: ProductWithVariation) : ProductDetailState()
}