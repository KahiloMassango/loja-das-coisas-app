package com.example.store.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.DefaultProductRepository
import com.example.store.core.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

): ViewModel() {
    private val productRepository = DefaultProductRepository()

    var searchQuery by mutableStateOf("")
        private set


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults: StateFlow<List<Product>> = snapshotFlow { searchQuery }
            .debounce(200)
            .mapLatest {
                when{
                    searchQuery.isNotEmpty() -> productRepository.getAllProducts().filter { product ->
                        product.name.contains(searchQuery, ignoreCase = true) or
                                product.description.contains(searchQuery, ignoreCase = true)
                    }
                    else -> emptyList()
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

}