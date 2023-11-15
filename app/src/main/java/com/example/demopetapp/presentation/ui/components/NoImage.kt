package com.example.demopetapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.demopetapp.R

@Composable
fun NoImage() {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.07f))
    ) {
        Text(
            text = stringResource(id = R.string.image_not_available).uppercase(),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}
