package com.luca.expedicao.userinterface

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CoresExpedicao = lightColorScheme(
    primary = Color(0xFF1F6F5C),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFCBEDE4),
    onPrimaryContainer = Color(0xFF073B31),
    secondary = Color(0xFF7C5E3C),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF4E3CF),
    onSecondaryContainer = Color(0xFF4A331B),
    background = Color(0xFFF8FAF9),
    onBackground = Color(0xFF1A1C1B),
    surface = Color.White,
    onSurface = Color(0xFF1A1C1B),
    surfaceVariant = Color(0xFFE7ECE9),
    onSurfaceVariant = Color(0xFF414945),
    tertiary = Color(0xFF3E6B8A),
    onTertiary = Color.White,
    error = Color(0xFFBA1A1A),
    onError = Color.White,
)

@Composable
fun ExpedicaoTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = CoresExpedicao, content = content)
}
