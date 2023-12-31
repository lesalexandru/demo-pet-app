package com.example.demopetapp.presentation.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

enum class Direction {
    Right, Left
}

fun slideAndFadeIn(from: Direction, animationDuration: Int = 300): EnterTransition =
    slideInHorizontally(
        initialOffsetX = {
            when (from) {
                Direction.Right -> 300
                Direction.Left -> -300
            }
        },
        animationSpec = tween(animationDuration)
    ) + fadeIn(
        animationSpec = tween(animationDuration)
    )

fun slideAndFadeOut(to: Direction, animationDuration: Int = 300): ExitTransition =
    slideOutHorizontally(
        targetOffsetX = {
            when (to) {
                Direction.Right -> 300
                Direction.Left -> -300
            }
        },
        animationSpec = tween(animationDuration)
    ) + fadeOut(
        animationSpec = tween(animationDuration)
    )