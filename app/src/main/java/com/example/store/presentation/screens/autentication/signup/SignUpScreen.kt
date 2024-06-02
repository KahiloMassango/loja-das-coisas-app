package com.example.store.presentation.screens.autentication.signup

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.R
import com.example.store.presentation.common.CustomButton
import com.example.store.presentation.common.CustomTextField
import com.example.store.presentation.common.SocialAuthButton
import com.example.store.presentation.common.ThemePreviews
import com.example.store.presentation.common.StoreLargeTopBar
import com.example.store.presentation.screens.autentication.components.CustomClickableText
import com.example.store.ui.theme.StoreTheme

@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            StoreLargeTopBar(
                title = "Criar conta",
                canNavigateBack = true,
                onNavigateUp = navController::navigateUp
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    modifier = Modifier,
                    value = name,
                    label = "Nome",
                    onValueChange = { name = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    modifier = Modifier,
                    value = phoneNumber,
                    label = "Telefone",
                    onValueChange = { phoneNumber = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    modifier = Modifier,
                    value = email,
                    label = "Email",
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
                    label = "Senha",
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
                CustomClickableText(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Já tem uma conta?",
                    supportText = "Entrar",
                    onClick = { navController.navigateUp() }
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "CRIAR CONTA",
                    onClick =  { /* TODO */ }
                )
                Spacer(modifier = Modifier.weight(1f))
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ){
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
        SignUpScreen(rememberNavController())
    }
}