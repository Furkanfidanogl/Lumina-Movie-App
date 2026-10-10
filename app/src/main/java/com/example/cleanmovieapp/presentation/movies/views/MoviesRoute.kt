package com.example.cleanmovieapp.presentation.movies.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.cleanmovieapp.presentation.movies.viewmodel.MoviesViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue

@Composable
fun MoviesRoute(
    modifier: Modifier = Modifier,
    onMovieClick: (Int) -> Unit,
    viewModel: MoviesViewModel = koinViewModel()
)
{
    val uiState by viewModel.moviesUiState.collectAsState()


    MoviesScreen(
        modifier = modifier , onMovieClick = onMovieClick , uiState = uiState , getMovies = viewModel::getMovies , getSearchMovies = viewModel::getSearchMovies , loadNextPage = viewModel::loadNextPage
    )
}