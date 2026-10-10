package com.example.cleanmovieapp.presentation.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmovieapp.domain.model.Movie
import com.example.cleanmovieapp.domain.use_case.GetMoviesSearchUseCase
import com.example.cleanmovieapp.domain.use_case.GetMoviesUseCase
import com.example.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class MoviesUiState(
    val movies: List<Movie> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val endReached: Boolean = false,
    val errorMessage: String? = null
)


class MoviesViewModel(private val getMovieUseCase: GetMoviesUseCase, private val getMovieSearchUseCase: GetMoviesSearchUseCase) : ViewModel() {
    private val _moviesUiState = MutableStateFlow(MoviesUiState())
    val moviesUiState = _moviesUiState.asStateFlow()

    private var currentPage = 1

    init {
        getMovies()
    }


    fun getMovies() {
        currentPage = 1

        viewModelScope.launch {
            _moviesUiState.update {
                it.copy(
                    isLoading = true,
                    isLoadingMore = false,
                    endReached = false,
                    searchQuery = "",
                    errorMessage = null
                )
            }

            when (val result = getMovieUseCase(currentPage)) {
                is Resource.Success -> {
                    val movies = result.data.orEmpty()

                    _moviesUiState.update {
                        it.copy(
                            movies = movies,
                            isLoading = false,
                        )
                    }

                    if (movies.isNotEmpty()) {
                        currentPage++
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


    // Pagination
    fun loadNextPage() {
        if (
            _moviesUiState.value.searchQuery.isNotBlank() ||
            _moviesUiState.value.isLoadingMore ||
            _moviesUiState.value.endReached
        ) return


        _moviesUiState.update {
            it.copy(
                isLoadingMore = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            when (val result = getMovieUseCase(currentPage)) {
                is Resource.Success -> {
                    val newMovies = result.data.orEmpty()

                    if (newMovies.isNotEmpty()) {
                        currentPage++
                    }

                    _moviesUiState.update {
                        it.copy(
                            movies = (it.movies + newMovies)
                                .distinctBy { movie -> movie.id },
                            isLoadingMore = false,
                            endReached = newMovies.isEmpty()
                        )
                    }
                }
                is Resource.Error -> {
                    _moviesUiState.update {
                        it.copy(
                            isLoadingMore = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }


    fun getSearchMovies(search: String) {
        viewModelScope.launch {
            _moviesUiState.update {
                it.copy(
                    isLoading = true,
                    searchQuery = search,
                    errorMessage = null
                )
            }

            when (val result = getMovieSearchUseCase(search)) {
                is Resource.Success -> {
                    _moviesUiState.update {
                        it.copy(
                            movies = result.data.orEmpty(),
                            isLoading = false,
                            errorMessage = null
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