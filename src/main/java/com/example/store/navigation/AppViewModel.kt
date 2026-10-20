package com.example.store.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.AccountRepository
import com.example.store.core.data.repository.CartRepository
import com.example.store.core.data.repository.SyncRepository
import com.example.store.core.data.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val networkMonitor: NetworkMonitor,
    private val accountRepository: AccountRepository,
    private val syncRepository: SyncRepository
) : ViewModel() {

    var showSplashScreen by mutableStateOf(true)
        private set

    val isLoggedInFlow = accountRepository.isUserLoggedIn()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            false
        ).also {
            viewModelScope.launch { delay(2000)
                showSplashScreen = false
            }
        }

    init {
        viewModelScope.launch {
            syncRepository.sync()
        }
    }

    val isOffline = networkMonitor.isOnline
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            false
        )


    val cartCount = cartRepository.getCartProductsCountStream()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0
        )

}