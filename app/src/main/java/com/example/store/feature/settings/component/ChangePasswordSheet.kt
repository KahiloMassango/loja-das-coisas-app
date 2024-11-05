package com.example.store.feature.settings.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordSheet(
    modifier: Modifier = Modifier,
    state: SheetState,
    onSave: (oldPassword: String, newPassword: String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var oldPassword by rememberSaveable { mutableStateOf("") }

    val passwordMatches by remember { derivedStateOf {
            newPassword == repeatPassword
        }
    }

    ModalBottomSheet(
        sheetState = state,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = Color(0xFF000000).copy(0.3f),
        contentColor = MaterialTheme.colorScheme.onSurface,
        onDismissRequest = onDismissRequest,
        contentWindowInsets = { WindowInsets.ime.union(WindowInsets.navigationBars) },
        dragHandle = {
            CustomDragHandle("Alterar palavra-passe")
        }
    ) {
        val focusManager = LocalFocusManager.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value =  oldPassword,
                placeholder = "Palavra-passe actual",
                onValueChange = { oldPassword = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            CustomClickableText(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                text = "Esqueceu sua palavra-passe?",
                supportText = "",
                onClick = { /* TODO */ }
            )
            StoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value = newPassword,
                placeholder = "Nova palavra-passe",
                supportingText = "(Mínimo 8 caracteres)",
                onValueChange = { newPassword = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            StoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value = repeatPassword,
                placeholder = "Repita a palavra-passe",
                isError = !passwordMatches,
                supportingText = if(passwordMatches) null else "Palavra-passe não correspondem",
                onValueChange = { repeatPassword = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus(true)
                    }
                )
            )
            CustomButton(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Salvar",
                enabled = passwordMatches,
                onClick = {
                    onSave(oldPassword, newPassword)
                }
            )
        }
    }
}
