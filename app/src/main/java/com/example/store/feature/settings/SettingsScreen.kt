package com.example.store.feature.settings

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.StoreTextField
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.settings.component.ChangePasswordSheet
import com.example.store.feature.settings.component.NotificationPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
) {
    val notificationPreference by viewModel.notificationPreference.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    var name by remember { mutableStateOf("") }
    val passwordSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    var showPasswordSheet by remember { mutableStateOf(false) }



    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(
                title = "Definições",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .padding(paddingValues)

        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
            ) {
                Text(
                    text = "Informações Pessoais",
                    style = MaterialTheme.typography.titleSmall,
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
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.clickable {
                            showPasswordSheet = true
                        },
                        text = "Alterar",
                        style = MaterialTheme.typography.labelSmall,
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
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(26.dp))
                NotificationPreferences(
                    notificationPreference = notificationPreference,
                    onNotificationPreferenceChange = { viewModel.updateNotificationPreference(it) }
                )
                Spacer(modifier = Modifier.weight(1f))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sair",
                    onClick = { /* TODO */ }
                    )
            }
        }
        if (showPasswordSheet) {
            ChangePasswordSheet(
                state = passwordSheetState,
                onSave = { _, _ ->
                    coroutineScope.launch {
                        delay(200)
                        passwordSheetState.hide()
                    }.invokeOnCompletion { showPasswordSheet = false }
                },
                onDismissRequest = { showPasswordSheet = false }
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        SettingsScreen {}
    }
}