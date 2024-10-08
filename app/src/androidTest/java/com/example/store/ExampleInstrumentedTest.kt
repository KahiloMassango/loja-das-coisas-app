package com.example.store

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.store.feature.autentication.forgot.ForgotPasswordScreen
import com.example.store.core.ui.theme.StoreTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/ tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun Assert_ForgotPasswordScreen_has_description_text() {
        composeRule.setContent {
            StoreTheme {
                ForgotPasswordScreen {}
            }
        }

        composeRule.onNodeWithText("Recuperar senha").assertIsDisplayed()


        composeRule.onNodeWithText("Email").assertIsDisplayed()

        composeRule.onNodeWithText("ENVIAR").assertIsDisplayed()

    }
}