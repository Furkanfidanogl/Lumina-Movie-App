package com.example.cleanmovieapp.presentation.welcome.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WelcomeRoute(
    modifier: Modifier = Modifier,
    onNavigateToMovies: () -> Unit = {}
)
{
    WelcomeScreen(modifier = modifier,onNavigateToMovies = onNavigateToMovies)
}