package com.example.store.feature.home

import androidx.lifecycle.ViewModel
import com.example.store.core.data.mock.productList
import com.example.store.core.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {

    private var _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Success(productList))
    val uiState = _uiState.asStateFlow()


}

sealed interface HomeUiState {
    data object Loading: HomeUiState
    data class Success(val products: List<Product>): HomeUiState
    data class Error(val message: String): HomeUiState

}