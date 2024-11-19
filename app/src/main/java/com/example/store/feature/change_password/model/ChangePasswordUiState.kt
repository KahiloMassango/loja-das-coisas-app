package com.example.store.feature.change_password.model

data class ChangePasswordUiState(
    val newPassword: String = "",
    val repeatPassword: String = ""
)