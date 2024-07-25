package com.example.store.presentation.screens.reviews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.ProductRepositoryImpl
import com.example.store.core.model.Rating
import com.example.store.core.model.RatingInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val productRepository = ProductRepositoryImpl()
    private val productId = savedStateHandle.get<String>("productId")!!

    private val _uiState = MutableStateFlow<ReviewsUiState>(ReviewsUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val ratings = productRepository.getProductRatings(productId).filter { it.comment != null }
                val ratingInfo = productRepository.getProductRatingInfo(productId)
                _uiState.value = ReviewsUiState.Success(ratings, ratingInfo)
            } catch (e: Exception) {
                _uiState.value = ReviewsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addReview(rate: Int, comment: String?) {
        /* TODO */
    }

}

sealed class ReviewsUiState {
    data object Loading: ReviewsUiState()
    data class Success(val ratings: List<Rating>, val ratingInfo: RatingInfo): ReviewsUiState()
    data class Error(val message: String): ReviewsUiState()
}