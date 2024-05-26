package com.example.store.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    secondary = lightSecondary,
    tertiary = lightTertiary,

    background = lightSurface,
    surface = lightSurface,
    onPrimary = lightOnPrimary,
    onSecondary = lightOnSecondary,
    secondaryContainer = lightSecondaryContainer,
    onSecondaryContainer = lightOnSecondaryContainer,
    onTertiary = lightOnTertiary,
    onBackground = lightOnSurface,
    onSurface = lightOnSurface,
    inverseOnSurface = lightInverseOnSurface,
    error = lightError,
    scrim = lightScrim
)

val DarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    secondary = darkSecondary,
    tertiary = darkNeutral,

    background = darkSurface,
    surface = darkSurface,
    onPrimary = darkOnPrimary,
    onSecondary = darkOnSecondary,
    onTertiary = darkOnNeutral,
    onBackground = darkOnSurface,
    onSurface = darkOnSurface,
    inverseOnSurface = darkInverseOnSurface,

    secondaryContainer = darkSecondaryContainer,
    onSecondaryContainer = darkOnSecondaryContainer,
    error = darkError,
    scrim = darkScrim,
)

@Composable
fun StoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = shape,
        content = content,
    )
}