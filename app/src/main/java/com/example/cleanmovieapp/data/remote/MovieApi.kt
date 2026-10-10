package com.example.cleanmovieapp.data.remote

import com.example.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.example.cleanmovieapp.data.remote.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{

    @GET("movie/top_rated")
    suspend fun getMovies(
        @Query("page") page : Int
    ): MoviesDto

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int
    ): MovieDetailDto

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") movie: String
    ): MoviesDto

}