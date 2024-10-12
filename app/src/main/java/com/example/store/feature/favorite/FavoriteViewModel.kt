package com.example.store.feature.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.feature.favorite.model.PriceSortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
): ViewModel() {

    private var _sortType = MutableStateFlow(PriceSortType.Ascending)
    val sortType = _sortType.asStateFlow()

    val products = combine(
        sortType,
        favoriteRepository.getFavoriteProductsStream()
    ) { priceOrdering, favoriteProducts ->
        when(priceOrdering){
            PriceSortType.Ascending -> favoriteProducts.sortedBy { it.price }
            PriceSortType.Descending -> favoriteProducts.sortedByDescending { it.price }
        }
    }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFavoriteProduct(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                favoriteRepository.removeFavoriteProduct(productId)
            } catch (e: Exception) {
                Log.d("FavoriteViewModel", "removeFavoriteProduct: $e")
            }

        }
    }

    fun setSorting(priceSortType: PriceSortType) {
        _sortType.value = priceSortType
    }
}