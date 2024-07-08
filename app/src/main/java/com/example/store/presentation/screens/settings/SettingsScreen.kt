package com.example.store.presentation.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.store.presentation.component.StoreLargeTopBar
import com.example.store.presentation.component.StoreTextField
import com.example.store.presentation.component.ThemePreviews
import com.example.store.presentation.screens.settings.component.NotificationPreferences
import com.example.store.presentation.screens.settings.component.PasswordChangeContainer
import com.example.store.ui.theme.StoreTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current
    var name by remember { mutableStateOf("") }
    var showPasswordChangeContainer by remember { mutableStateOf(false) }
    val passwordChangeContainerState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()

    if (showPasswordChangeContainer) {
        PasswordChangeContainer(
            state = passwordChangeContainerState,
            onSave = { _, _ ->
                coroutineScope.launch {
                    delay(200)
                    passwordChangeContainerState.hide()
                }.invokeOnCompletion { showPasswordChangeContainer = false }
            },
            onDismissRequest = { showPasswordChangeContainer = false }
        )
    }

    Scaffold(
        modifier = modifier.pointerInput(null) {
            detectTapGestures(
                onTap = {
                    focusManager.clearFocus()
                }
            )
        },
        topBar = {
            StoreLargeTopBar(
                title = "Definições",
                canNavigateBack = true
            ) { navController.navigateUp() }
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 18.dp, bottom = 16.dp)
                    .fillMaxSize(),
            ) {
                Text(
                    text = "Informações Pessoais",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    placeholder = "Nome Completo",
                    onValueChange = { name = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                Spacer(modifier = Modifier.height(45.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.clickable {
                            showPasswordChangeContainer = true
                        },
                        text = "Alterar",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface,

                        )
                }
                Spacer(modifier = Modifier.height(16.dp))
                StoreTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "Password12345",
                    placeholder = "Password",
                    enabled = false,
                    onValueChange = { name = it },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(45.dp))
                Text(
                    text = "Notificações",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(26.dp))
                NotificationPreferences()
            }
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        SettingsScreen(
            navController = rememberNavController()
        )
    }
}