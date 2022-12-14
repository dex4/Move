package com.pose.move.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MoveColorScheme = lightColorScheme(
    primary = Pink,
    onPrimary = White,
    background = White,
    onBackground = ViolentViolet,
    primaryContainer = Jagger,
    onPrimaryContainer = White,
    error = Red,
    onError = White,
    onSurfaceVariant = White60
)

@Composable
fun MoveTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MoveColorScheme,
        typography = Typography,
        content = content
    )
}