package com.sevbanbayir.multifunctionalnoteapp.feature_note.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = Color(0xff000000),
    onBackground = Color.White,
    surface = LightBlue,
    onSurface = DarkGray
)

@Composable
fun MultiFunctionalNoteAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}