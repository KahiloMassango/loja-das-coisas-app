package com.example.store.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val networkMonitor: NetworkMonitor,
): ViewModel() {

    var isOffline = mutableStateOf(true)
        private set

    init {
        isOffline.value = !networkMonitor.hasNetworkConnection()
    }

    val cartCount = cartRepository.getCartProductsCountStream()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0
        )

    fun tryAgain() {
        isOffline.value = !networkMonitor.hasNetworkConnection()

    }

}