package com.example.cleanmovieapp.domain.use_case

import com.example.cleanmovieapp.domain.model.MovieDetail
import com.example.cleanmovieapp.domain.repository.MovieRepository
import com.example.cleanmovieapp.util.Resource
import okio.IOException

class GetMovieDetailUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(id : Int): Resource<MovieDetail> {
        return try {
            Resource.Success(repository.getMovieDetail(id))
        } catch (e: IOException) {
            Resource.Error(
                message = "Network Error"
            )
        } catch (e: Exception) {
            Resource.Error(
                message = "Unknown Error"
            )
        }
    }

}