package com.example.demopetapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.demopetapp.presentation.detail.AnimalDetailsScreen
import com.example.demopetapp.presentation.list.AnimalsScreen

private const val ID_ARG = "id"

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    val actions = remember(navController) { NavigationActions(navController) }

    NavHost(
        navController = navController,
        startDestination = Screen.AnimalsScreen.route,
    ) {
        addAnimalsScreen(actions)
        addAnimalDetailsScreen()
    }
}

private fun NavGraphBuilder.addAnimalsScreen(
    actions: NavigationActions,
) {
    composable(
        route = Screen.AnimalsScreen.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
            }
        ),
        enterTransition = {
            when (initialState.destination.route) {
                Screen.DetailsScreen.route -> slideAndFadeIn(from = Direction.Left)

                else -> null
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                Screen.DetailsScreen.route -> slideAndFadeOut(to = Direction.Left)

                else -> null
            }
        }
    ) {
        AnimalsScreen(actions)
    }
}

private fun NavGraphBuilder.addAnimalDetailsScreen() {
    composable(
        route = Screen.DetailsScreen.route,
        arguments = listOf(
            navArgument(ID_ARG) {
                type = NavType.LongType
            }
        ),
        enterTransition = {
            when (initialState.destination.route) {
                Screen.AnimalsScreen.route -> {
                    slideAndFadeIn(from = Direction.Right)
                }
                else -> null
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                Screen.AnimalsScreen.route -> {
                    slideAndFadeOut(to = Direction.Right)
                }
                else -> null
            }
        }
    ) {
        AnimalDetailsScreen(it.arguments?.getLong(ID_ARG))
    }
}