package com.example.moviescity.modules.coming_soon_movies_list.domain.model

data class DomainUpComingSoonMoviesResult(
    val id: Int,
    val adult: Boolean,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)
