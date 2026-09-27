package com.example.cleanmovieapp.di.module

import com.example.cleanmovieapp.domain.use_case.GetMovieDetailUseCase
import com.example.cleanmovieapp.domain.use_case.GetMoviesUseCase
import com.example.cleanmovieapp.domain.use_case.GetMoviesSearchUseCase
import com.example.cleanmovieapp.presentation.movie_detail.viewmodel.MovieDetailViewModel
import com.example.cleanmovieapp.presentation.movies.viewmodel.MoviesViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::GetMoviesUseCase)
    factoryOf(::GetMoviesSearchUseCase)
    viewModelOf(::MoviesViewModel)

    factoryOf(::GetMovieDetailUseCase)
    viewModelOf(::MovieDetailViewModel)

}