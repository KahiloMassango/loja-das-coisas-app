package com.example.store.features.authentication.forgot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.StoreTextField
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(
                title = "Recuperar senha",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .padding(start = 16.dp,end = 16.dp,top = 64.dp,bottom = 16.dp)
                    .fillMaxSize(),
            ) {
                Text(
                    text = "Por favor, indique o seu endereço de e-mail." +
                        " Você receberá um link para criar uma nova senha por e-mail.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,

                )
                Spacer(modifier = Modifier.height(18.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    placeholder = "Email",
                    onValueChange = { email = it },
                    isError = false,
                    supportingText = "Não é um endereço de e-mail válido. Deve ser seu@email.com",
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.clearFocus()
                        }
                    )
                )
                Spacer(modifier = Modifier.height(18.dp))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "ENVIAR",
                    onClick = { /* TODO */ }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ForgotPasswordScreen{}
    }
}