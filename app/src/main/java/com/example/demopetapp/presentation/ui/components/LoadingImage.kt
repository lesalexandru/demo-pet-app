package com.example.demopetapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingImage() {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.07f))
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}