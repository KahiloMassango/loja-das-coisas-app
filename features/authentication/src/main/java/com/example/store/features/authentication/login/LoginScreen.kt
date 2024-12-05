package com.example.store.features.authentication.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.store.core.ui.R
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.SocialAuthButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.StoreTextField
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.authentication.component.CustomClickableText

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel() ,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    val focusManager = LocalFocusManager.current




    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(
                title = "Login",
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
                    .padding(start = 16.dp, end = 16.dp, top = 54.dp, bottom = 16.dp)
                    .fillMaxSize(),
            ) {
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.email,
                    placeholder = "Email",
                    isError = false,
                    supportingText = "Not a valid email. Should be your@email.com.",
                    onValueChange = { viewModel.updateEmail(it) },
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
                Spacer(modifier = Modifier.height(20.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.password,
                    placeholder = "Password",
                    onValueChange = { viewModel.updatePassword(it) },
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

                Spacer(modifier = Modifier.height(26.dp))
                Box(modifier = Modifier.fillMaxWidth()){
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { onForgotPassword() },
                        text = "Esqueceu a password?",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "ENTRAR",
                    onClick = { /* TODO */ }
                )
                Spacer(modifier = Modifier.height(30.dp))
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
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
                Spacer(modifier = Modifier.height(26.dp))
                CustomClickableText(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Não tem uma conta?",
                    supportText = "Criar",
                    onClick =  { onSignUp() }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        //LoginScreen(Modifier, {}, {}, {})
    }
}