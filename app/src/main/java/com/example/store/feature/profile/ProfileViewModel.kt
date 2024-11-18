package com.example.store.feature.profile

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.example.store.core.model.Gender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@HiltViewModel
class ProfileViewModel(

): ViewModel() {

    var userPhoto: Uri? by mutableStateOf(null)
        private set

    var userName by mutableStateOf("Matilda Brown",)
        private set

    var userEmail by mutableStateOf("matildabrown@mail.com")
        private set

    var userPhoneNumber by mutableStateOf("+244 929851709")
        private set

    val userGender by mutableStateOf(Gender.MALE)

    fun updateUsername(username: String) {
        userName = username
    }

    fun updateEmail(email: String) {
        userEmail = email
    }

    fun updatePhoneNumber(phoneNumber: String) {
        userPhoneNumber = phoneNumber
    }

    fun setPhoto(newUri: Uri) {
        userPhoto = newUri

    }


    fun saveProfile() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }


}