package com.example.cleanmovieapp.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val tagline: String?,
    val runtime: Int?,
    val homepage: String?,
    val adult: Boolean,
    val posterPath: String?,
    val backdropPath: String?
)