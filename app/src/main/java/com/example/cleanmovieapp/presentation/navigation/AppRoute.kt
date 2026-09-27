package com.example.cleanmovieapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class AppRoute {

    @Serializable
    data object WelcomeScreen : AppRoute()

    @Serializable
    data object MoviesScreen : AppRoute()

    @Serializable
    data class MovieDetailScreen(val id: Int) : AppRoute()
}
