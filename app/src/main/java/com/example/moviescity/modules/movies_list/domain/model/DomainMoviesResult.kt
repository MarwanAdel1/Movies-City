package com.example.moviescity.modules.movies_list.domain.model

data class DomainMoviesResult(
    val id: Int,
    val adult: Boolean,
    val posterPath: String?,
    val title: String,
    val voteAverage: Double
)
