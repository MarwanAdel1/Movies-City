package com.example.moviescity.modules.movies_list_adapter.presentation.model

data class AdapterMovieModel(
    val id: Int,
    val adult: Boolean,
    val posterPath: String?,
    val title: String,
    val voteAverage: Double
)