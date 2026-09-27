package com.example.cleanmovieapp.presentation.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmovieapp.domain.model.Movie
import com.example.cleanmovieapp.domain.use_case.GetMoviesUseCase
import com.example.cleanmovieapp.domain.use_case.GetMoviesSearchUseCase
import com.example.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MoviesUiState(
    val movies: List<Movie> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String ?= null
)

class MoviesViewModel(private val getMovieUseCase: GetMoviesUseCase, private val getMovieSearchUseCase: GetMoviesSearchUseCase) : ViewModel() {
    private val _moviesUiState = MutableStateFlow(MoviesUiState())
    val moviesUiState = _moviesUiState.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {

            _moviesUiState.update {
                it.copy(
                    isLoading = true,
                )
            }

            when (val result = getMovieUseCase()) {

                is Resource.Success -> {
                    _moviesUiState.update {
                        it.copy(
                            movies = result.data ?: emptyList(),
                            isLoading = false,
                        )
                    }
                }

                is Resource.Error -> {
                    _moviesUiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }


    fun getSearchMovies(search : String) {
        viewModelScope.launch {

            _moviesUiState.update {
                it.copy(
                    isLoading = true,
                    searchQuery = search
                )
            }

            when (val result = getMovieSearchUseCase(search)) {

                is Resource.Success -> {
                    _moviesUiState.update {
                        it.copy(
                            movies = result.data ?: emptyList(),
                            isLoading = false,
                        )
                    }
                }

                is Resource.Error -> {
                    _moviesUiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
}