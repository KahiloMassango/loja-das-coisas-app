package com.example.store.feature.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.datastore.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesRepository: PreferencesRepository
) : ViewModel() {

    val notificationPreference = userPreferencesRepository.getNotificationPreference()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )

    fun updateNotificationPreference(receiveNotification: Boolean) {
        viewModelScope.launch {
            try {
                userPreferencesRepository.updateNotificationPreference(receiveNotification)
            } catch (e: Exception) {
                Log.d("SettingsViewModel", "removeProductFromCart: $e")
            }
        }
    }

}