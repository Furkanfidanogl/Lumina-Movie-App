package com.example.cleanmovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cleanmovieapp.presentation.movie_detail.views.MovieDetailScreen
import com.example.cleanmovieapp.presentation.movies.views.MoviesScreen
import com.example.cleanmovieapp.presentation.navigation.AppRoute
import com.example.cleanmovieapp.presentation.ui.theme.CleanMovieAppTheme
import com.example.cleanmovieapp.presentation.welcome.views.WelcomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()

            CleanMovieAppTheme {
                NavHost(
                    navController = navController,
                    modifier = Modifier.fillMaxSize(),
                    startDestination = AppRoute.WelcomeScreen
                ) {

                    composable<AppRoute.WelcomeScreen> {
                        WelcomeScreen(
                            onNavigateToMovies = {
                                navController.navigate(AppRoute.MoviesScreen) {
                                    // Pop up to the root of the back stack
                                    popUpTo<AppRoute.WelcomeScreen> {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable<AppRoute.MoviesScreen> {
                        MoviesScreen(
                            onMovieClick = { movieId ->
                                navController.navigate(AppRoute.MovieDetailScreen(id = movieId))
                            }
                        )
                    }
                    composable<AppRoute.MovieDetailScreen> { backStackEntry ->
                        val route = backStackEntry.toRoute<AppRoute.MovieDetailScreen>()
                        MovieDetailScreen(
                            id = route.id,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}