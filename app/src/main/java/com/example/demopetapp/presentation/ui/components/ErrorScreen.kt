package com.example.demopetapp.presentation.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun BoxScope.ErrorScreen(message: String) {
    SingleLineText(
        text = message,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Normal
    )
}