package com.example.store.presentation.screens.settings.model

data class ChangePasswordUiState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val repeatPassword: String = ""
)