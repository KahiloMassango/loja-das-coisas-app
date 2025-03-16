package com.example.store.features.discover

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.core.data.repository.GenderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val genderRepository: GenderRepository,
): ViewModel() {

    private var _genders = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val genders = _genders.asStateFlow()

    init {
        viewModelScope.launch {
            val test = genderRepository.getGendersWithCategories()
                .also {
                    _genders.value = it
                }
            Log.d("DiscoverViewModel", "genders: $test")

        }
    }

}