package com.example.store.presentation.screens.autentication.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBarDefaults
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
import com.example.store.navigation.Screen
import com.example.store.presentation.component.CustomButton
import com.example.store.presentation.component.SocialAuthButton
import com.example.store.presentation.component.StoreLargeTopBar
import com.example.store.presentation.component.StoreTextField
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.autentication.component.CustomClickableText
import com.example.store.ui.theme.StoreTheme

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(
                title = "Login",
                canNavigateBack = true,
                onNavigateUp = navController::navigateUp
            )
        },
        contentWindowInsets = WindowInsets.statusBars.exclude(BottomAppBarDefaults.windowInsets)
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
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
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
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    label = "Password",
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
                    modifier = Modifier,
                    text = "Esqueceu sua password?",
                    onClick = { navController.navigate(Screen.ForgotPassword) }
                )
                Spacer(modifier = Modifier.height(32.dp))
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
                CustomClickableText(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Não tem uma conta?",
                    supportText = "Criar",
                    textArrangement = Arrangement.Center,
                    onClick =  { navController.navigate(Screen.SignUp) }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        LoginScreen(rememberNavController())
    }
}