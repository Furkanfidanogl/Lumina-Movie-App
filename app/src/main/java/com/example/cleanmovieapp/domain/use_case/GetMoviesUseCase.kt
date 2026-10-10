package com.example.cleanmovieapp.domain.use_case

import com.example.cleanmovieapp.domain.model.Movie
import com.example.cleanmovieapp.domain.repository.MovieRepository
import com.example.cleanmovieapp.util.Resource
import okio.IOException

class GetMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(page : Int): Resource<List<Movie>> {
        return try {
            Resource.Success(
                repository.getMovies(page)
            )
        } catch (e: IOException) {
            Resource.Error(
                message = e.message ?: "Network error"
            )
        } catch (e: Exception) {
            Resource.Error(
                message = e.message ?: "Unknown error"
            )
        }
    }
}