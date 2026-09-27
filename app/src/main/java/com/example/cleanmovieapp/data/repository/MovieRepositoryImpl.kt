package com.example.cleanmovieapp.data.repository

import com.example.cleanmovieapp.data.remote.MovieApi
import com.example.cleanmovieapp.data.remote.dto.toMovieDetail
import com.example.cleanmovieapp.data.remote.dto.toMovieList
import com.example.cleanmovieapp.domain.model.Movie
import com.example.cleanmovieapp.domain.model.MovieDetail
import com.example.cleanmovieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(private val api: MovieApi) : MovieRepository {

    override suspend fun getMovies(): List<Movie> {
        return api.getMovies().toMovieList()
    }

    override suspend fun getMovieDetail(id: Int): MovieDetail {
        return api.getMovieDetail(id = id).toMovieDetail()
    }

    override suspend fun getSearchMovie(movie: String): List<Movie> {
        return api.getSearchMovie(movie = movie).toMovieList()
    }

}