package com.example.cleanmovieapp.domain.use_case

import com.example.cleanmovieapp.domain.model.Movie
import com.example.cleanmovieapp.domain.repository.MovieRepository
import com.example.cleanmovieapp.util.Resource
import okio.IOException

class GetMoviesSearchUseCase (private val repository: MovieRepository) {

    suspend operator fun invoke(search : String): Resource<List<Movie>> {
        return try {
            Resource.Success(
                repository.getSearchMovie(search)
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