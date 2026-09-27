package com.example.cleanmovieapp.presentation.movie_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmovieapp.domain.model.MovieDetail
import com.example.cleanmovieapp.domain.use_case.GetMovieDetailUseCase
import com.example.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MovieDetailUiState(
    val movieDetail: MovieDetail? = null,
    val isLoading : Boolean = false,
    val errorMessage : String ?= null
)

class MovieDetailViewModel(private val getMovieDetailUseCase: GetMovieDetailUseCase) : ViewModel() {
    private val _movieDetailUiState = MutableStateFlow(MovieDetailUiState())
    val movieDetailUiState = _movieDetailUiState.asStateFlow()

    fun getDetail(id : Int) {
        viewModelScope.launch {
            _movieDetailUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            when(val result = getMovieDetailUseCase(id)){
                is Resource.Success -> {
                    _movieDetailUiState.update {
                        it.copy(
                            movieDetail = result.data,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    _movieDetailUiState.update {
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