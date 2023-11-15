package com.example.demopetapp.presentation.ui

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

class NavigationActions(navController: NavHostController) {

    val goToDetailsScreen: (Long) -> Unit = { id ->
        if (navController.currentBackStackEntry?.lifecycleIsResumed() == true) {
            navController.navigate(Screen.DetailsScreen.createRoute(id))
        }
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    private fun NavBackStackEntry.lifecycleIsResumed() =
        this.lifecycle.currentState == Lifecycle.State.RESUMED
}
