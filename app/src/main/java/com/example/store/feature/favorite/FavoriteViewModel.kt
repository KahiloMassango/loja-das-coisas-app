package com.example.store.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.FavoriteRepository
import com.example.store.core.database.model.asExternalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
): ViewModel() {

    val uiState = favoriteRepository.getFavoriteProductsStream()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFavoriteProduct(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavoriteProduct(productId)
        }

    }


}