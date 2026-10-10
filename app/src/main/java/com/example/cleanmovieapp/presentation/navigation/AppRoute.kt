package com.example.cleanmovieapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class AppRoute {

    @Serializable
    data object Welcome : AppRoute()

    @Serializable
    data object Movies : AppRoute()

    @Serializable
    data class MovieDetail(val id: Int) : AppRoute()
}
