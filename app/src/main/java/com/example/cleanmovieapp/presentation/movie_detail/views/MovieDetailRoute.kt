package com.example.cleanmovieapp.presentation.movie_detail.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.cleanmovieapp.presentation.movie_detail.viewmodel.MovieDetailViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue


@Composable
fun MovieDetailRoute(
    modifier: Modifier = Modifier,
    id: Int,
    onNavigateBack: () -> Unit,
    viewModel: MovieDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.movieDetailUiState.collectAsState()

    MovieDetailScreen(modifier = modifier , id = id , onNavigateBack = onNavigateBack , uiState = uiState , getDetail = viewModel::getDetail)
}