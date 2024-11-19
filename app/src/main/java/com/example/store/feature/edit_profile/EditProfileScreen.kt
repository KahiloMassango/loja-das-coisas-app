package com.example.store.feature.edit_profile

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.model.resource.isEmailValid
import com.example.store.core.model.resource.isNameValid
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.component.StoreTextField
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.edit_profile.component.GenderSelector
import com.example.store.feature.edit_profile.component.ProfileImagePicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    viewmodel: EditProfileViewModel = EditProfileViewModel(),
    onNavigateUp: () -> Unit = {}
) {
    val context = LocalContext.current
    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(
                title = "Editar Perfil",
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                            keyboardManager?.hide()
                        }
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImagePicker(
                    imageUrl = viewmodel.userPhoto,
                    onChangeImage = { viewmodel.setPhoto(it) }
                )
                Spacer(Modifier.height(34.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    StoreTextField(
                        value = viewmodel.userName,
                        onValueChange = { viewmodel.updateUsername(it) },
                        placeholder = "Nome",
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    StoreTextField(
                        value = viewmodel.userEmail,
                        onValueChange = { viewmodel.updateEmail(it) },
                        placeholder = "Email",
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    StoreTextField(
                        value = viewmodel.userPhoneNumber,
                        onValueChange = { viewmodel.updatePhoneNumber(it) },
                        placeholder = "Telefone",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardManager?.hide()
                                focusManager.clearFocus()
                            }
                        )
                    )
                    GenderSelector(
                        modifier = Modifier.fillMaxWidth(),
                        selectedGender = viewmodel.userGender,
                        onSelectGender = {},
                        enabled = false
                    )
                }

                Spacer(Modifier.height(34.dp))
                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(),
                    text = "Salvar Alterações",
                    onClick = {
                        when {
                            !isNameValid(viewmodel.userName) -> {
                                Toast.makeText(context, "Nome inválido", Toast.LENGTH_SHORT).show()
                            }
                            !isEmailValid(viewmodel.userEmail) -> {
                                Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            else -> {
                                viewmodel.saveProfile()
                            }
                        }
                    }
                )
            }
        }
    }
}






@Preview
@Composable
private fun Preview() {
    StoreTheme {
        EditProfileScreen()

    }
}