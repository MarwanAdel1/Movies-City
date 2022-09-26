package com.example.moviescity.modules.movies_list.presentation.model

data class ViewMoviesResult(
    val id: Int,
    val adult: Boolean,
    val posterPath: String?,
    val title: String,
    val voteAverage: Double
)