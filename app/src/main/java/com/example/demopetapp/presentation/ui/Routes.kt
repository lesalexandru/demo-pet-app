package com.example.demopetapp.presentation.ui

sealed class Screen(val route: String) {

    object AnimalsScreen : Screen("animals")

    object DetailsScreen : Screen("details/{id}") {
        fun createRoute(id: Long): String = "details/$id"
    }
}
