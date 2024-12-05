package com.example.store.feature.authentication.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.R
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.SocialAuthButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.StoreTextField
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.authentication.component.CustomClickableText

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateLogin: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val uiState = viewModel.uiState
    var showPassword by remember { mutableStateOf(false) }


    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StoreLargeTopBar(
                title = "Criar conta",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 34.dp, bottom = 20.dp)
                    .fillMaxSize(),
                // horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.name,
                    placeholder = "Nome",
                    isError = uiState.nameError,
                    supportingText = "(Mínimo 4 caracteres, letras apenas.)",
                    onValueChange = { viewModel.updateName(it) },

                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password,
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.email,
                    placeholder = "Email",
                    isError = uiState.emailError,
                    supportingText = "Ex: meu@email.com)",
                    onValueChange = { viewModel.updateEmail(it) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.phoneNumber,
                    placeholder = "Telefone",
                    isError = uiState.phoneNumberError,
                    supportingText = "*obrigatório",
                    onValueChange = { viewModel.updatePhoneNumber(it) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.password,
                    placeholder = "Senha",
                    isError = uiState.passwordError,
                    supportingText = "(Mínimo 8 caracteres)",
                    onValueChange = { viewModel.updatePassword(it) },
                    visualTransformation = if (showPassword) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        val imageVector = if (showPassword) Icons.Default.VisibilityOff else
                            Icons.Default.Visibility

                        IconButton(
                            onClick = { showPassword = ! showPassword }
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = imageVector,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.inverseOnSurface
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.confirmPassword,
                    placeholder = "Confirmar senha",
                    isError = uiState.confirmPasswordError,
                    supportingText = "As senhas não correspondem.",
                    onValueChange = { viewModel.updateConfirmPassword(it) },
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

                CustomClickableText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 26.dp),
                    text = "Já tem uma conta?",
                    supportText = "Entrar",
                    horizontalArrangement = Arrangement.End,
                    onClick = { onNavigateLogin() }
                )

                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "CRIAR CONTA",
                    onClick = { viewModel.validateForm() }
                )

                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Inscrever-se com",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    SocialAuthButton(
                        iconRes = R.drawable.google_icon,
                        onClick = { /* TODO */ }
                    )
                }
            }
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        SignUpScreen(
            onNavigateLogin = {},
            onNavigateUp = {}
        )
    }
}