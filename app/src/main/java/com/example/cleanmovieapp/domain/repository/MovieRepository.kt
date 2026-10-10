package com.example.cleanmovieapp.domain.repository


import com.example.cleanmovieapp.domain.model.Movie
import com.example.cleanmovieapp.domain.model.MovieDetail

interface MovieRepository {

    suspend fun getMovies(page : Int): List<Movie>

    suspend fun getMovieDetail(id: Int): MovieDetail

    suspend fun getSearchMovie(movie: String): List<Movie>

}