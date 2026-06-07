package com.example.praktam_2417051027.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val FoodColorScheme = lightColorScheme(
    primary = FoodPrimary,
    secondary = FoodSecondary,
    background = FoodBackground,
    surface = FoodSurface,
    onPrimary = FoodOnPrimary,
    onBackground = FoodOnBackground,
    onSurface = FoodOnSurface
)

@Composable
fun PrakTAM_2417051027Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FoodColorScheme,
        typography = Typography,
        content = content
    )
}