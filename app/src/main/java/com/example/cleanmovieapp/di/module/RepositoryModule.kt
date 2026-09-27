package com.example.cleanmovieapp.di.module

import com.example.cleanmovieapp.data.repository.MovieRepositoryImpl
import com.example.cleanmovieapp.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }
}