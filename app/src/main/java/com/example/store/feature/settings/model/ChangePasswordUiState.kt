package com.example.store.feature.settings.model

data class ChangePasswordUiState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val repeatPassword: String = ""
)