package com.example.store.presentation.screens.settings.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.store.presentation.component.CustomButton
import com.example.store.presentation.component.CustomDragHandle
import com.example.store.presentation.component.StoreTextField
import com.example.store.presentation.screens.autentication.component.CustomClickableText
import com.example.store.presentation.screens.settings.model.ChangePasswordUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordChangeContainer(
    modifier: Modifier = Modifier,
    state: SheetState,
    onSave: (oldPassword: String, newPassword: String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var uiState by remember { mutableStateOf(ChangePasswordUiState()) }

    ModalBottomSheet(
        sheetState = state,
        modifier = modifier.navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = Color(0xFF000000).copy(0.3f),
        contentColor = MaterialTheme.colorScheme.onSurface,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            CustomDragHandle("Alterar Password")
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StoreTextField(
                modifier = Modifier,
                value = uiState.oldPassword,
                label = "Old Password",
                onValueChange = { uiState = uiState.copy(oldPassword = it) },
                visualTransformation = PasswordVisualTransformation()
            )
            CustomClickableText(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Esqueceu sua password?",
                supportText = "",
                onClick = { /* TODO */ }
            )
            StoreTextField(
                modifier = Modifier,
                value = uiState.newPassword,
                label = "New Password",
                onValueChange = { uiState = uiState.copy(newPassword = it) },
                visualTransformation = PasswordVisualTransformation()
            )
            StoreTextField(
                modifier = Modifier,
                value = uiState.repeatPassword,
                label = "Repeat Password",
                onValueChange = { uiState = uiState.copy(repeatPassword = it) },
                visualTransformation = PasswordVisualTransformation()
            )
            CustomButton(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "SALVAR",
                onClick = {
                    onSave(uiState.oldPassword, uiState.newPassword)
                }
            )
        }
    }
}
