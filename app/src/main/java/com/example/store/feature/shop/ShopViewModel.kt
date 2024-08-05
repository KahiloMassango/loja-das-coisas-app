package com.example.store.feature.shop

import androidx.lifecycle.ViewModel
import com.example.store.core.data.ProductRepositoryImpl
import com.example.store.core.data.mock.productList
import com.example.store.core.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(

) : ViewModel() {

    private val productRepository = ProductRepositoryImpl()

    private val _uiState = MutableStateFlow<ShopUiState>(ShopUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getProducts(section: String, category: String, filter: String, orderBy: String) {
        try {
            val products = productList
            _uiState.value = ShopUiState.Success(products)
        } catch (e: Exception) {
            _uiState.value = ShopUiState.Error
        }

    }

}

sealed class ShopUiState {
    data object Loading : ShopUiState()
    data class Success(val products: List<Product>) : ShopUiState()
    data object Error : ShopUiState()

}