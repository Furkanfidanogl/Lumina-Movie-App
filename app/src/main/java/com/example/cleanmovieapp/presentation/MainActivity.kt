package com.example.cleanmovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cleanmovieapp.presentation.movie_detail.views.MovieDetailRoute
import com.example.cleanmovieapp.presentation.movies.views.MoviesRoute
import com.example.cleanmovieapp.presentation.navigation.AppRoute
import com.example.cleanmovieapp.presentation.ui.theme.CleanMovieAppTheme
import com.example.cleanmovieapp.presentation.welcome.views.WelcomeRoute

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
                    startDestination = AppRoute.Welcome
                ) {

                    composable<AppRoute.Welcome> {
                        WelcomeRoute (
                            onNavigateToMovies = {
                                navController.navigate(AppRoute.Movies) {
                                    // Pop up to the root of the back stack
                                    popUpTo<AppRoute.Welcome> {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable<AppRoute.Movies> {
                        MoviesRoute (
                            onMovieClick = { movieId ->
                                navController.navigate(AppRoute.MovieDetail(id = movieId))
                            }
                        )
                    }
                    composable<AppRoute.MovieDetail> { backStackEntry ->
                        val route = backStackEntry.toRoute<AppRoute.MovieDetail>()
                        MovieDetailRoute (
                            id = route.id,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}