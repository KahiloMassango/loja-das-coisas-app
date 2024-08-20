package com.example.store.feature.settings.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.CustomDragHandle
import com.example.store.core.ui.component.StoreTextField
import com.example.store.feature.autentication.component.CustomClickableText
import com.example.store.feature.settings.model.ChangePasswordUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordChangeContainer(
    modifier: Modifier = Modifier,
    state: SheetState,
    onSave: (oldPassword: String, newPassword: String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var uiState by remember { mutableStateOf(ChangePasswordUiState()) }
    val focusManager = LocalFocusManager.current

    ModalBottomSheet(
        sheetState = state,
        modifier = modifier,
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
                modifier = Modifier.fillMaxWidth(),
                value = uiState.oldPassword,
                placeholder = "Old Password",
                onValueChange = { uiState = uiState.copy(oldPassword = it) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )
            CustomClickableText(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                text = "Esqueceu sua senha?",
                supportText = "",
                onClick = { /* TODO */ }
            )
            StoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.newPassword,
                placeholder = "New Password",
                onValueChange = { uiState = uiState.copy(newPassword = it) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) })
            )
            StoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.repeatPassword,
                placeholder = "Repeat Password",
                onValueChange = { uiState = uiState.copy(repeatPassword = it) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
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
