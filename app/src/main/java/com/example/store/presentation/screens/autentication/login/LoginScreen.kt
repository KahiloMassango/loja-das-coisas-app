package com.example.store.presentation.screens.autentication.login

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.store.R
import com.example.store.presentation.common.CustomButton
import com.example.store.presentation.common.CustomTextField
import com.example.store.presentation.common.SocialAuthButton
import com.example.store.presentation.common.ThemePreviews
import com.example.store.presentation.common.LargeTopBar
import com.example.store.presentation.common.ClickableText
import com.example.store.ui.theme.StoreTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier,
        topBar = {
            LargeTopBar(title = "Login", canNavigateBack = true, onNavigateUp = onNavigateUp)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)

        ){
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 64.dp, bottom = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    modifier = Modifier,
                    value = email,
                    placeholder = "Email",
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    value = password,
                    placeholder = "Password",
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                Spacer(modifier = Modifier.height(14.dp))
                ClickableText(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Esqueceu sua password?",
                    onClick = onForgotPassword
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "ENTRAR",
                    onClick = { /* TODO */ }
                )
                Spacer(modifier = Modifier.weight(1f))
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    Text(
                        text = "Ou faça login com ",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    SocialAuthButton(
                        iconRes = R.drawable.google_icon,
                        onClick = { /* TODO */ }
                    )
                }
                Spacer(modifier = Modifier.height(46.dp))
                ClickableText(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Não tem uma conta?",
                    supportText = "Criar",
                    textArrangement = Arrangement.Center,
                    onClick = onSignUp
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        LoginScreen(
            onSignUp = {},
            onForgotPassword = {},
            onNavigateUp = {}
        )
    }
}