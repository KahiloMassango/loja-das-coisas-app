package com.example.store.presentation.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.store.presentation.screens.autentication.components.CustomClickableText
import com.example.store.presentation.common.CustomButton
import com.example.store.presentation.common.CustomDragHandle
import com.example.store.presentation.common.CustomTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChangePasswordContainer(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }

    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            CustomDragHandle("Alterar Password")
        }
    ) {
        Column(
            modifier = modifier

                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                modifier = Modifier,
                value = oldPassword,
                label = "Old Password",
                enabled = true,
                onValueChange = { oldPassword = it },
                visualTransformation = PasswordVisualTransformation()
            )
            CustomClickableText(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Esqueceu sua password?",
                supportText = "",
                onClick = { /* TODO */ }
            )
            CustomTextField(
                modifier = Modifier,
                value = newPassword,
                label = "New Password",
                enabled = true,
                onValueChange = { newPassword = it },
                visualTransformation = PasswordVisualTransformation()
            )
            CustomTextField(
                modifier = Modifier,
                value = rePassword,
                label = "Repeat Password",
                enabled = true,
                onValueChange = { rePassword = it },
                visualTransformation = PasswordVisualTransformation()
            )
            CustomButton(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "SALVAR",
                onClick = { /* TODO */ }
            )
        }
    }
}
