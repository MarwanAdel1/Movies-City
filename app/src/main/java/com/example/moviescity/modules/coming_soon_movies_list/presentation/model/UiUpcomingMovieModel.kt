package com.example.moviescity.modules.coming_soon_movies_list.presentation.model

data class UiUpcomingMovieModel(
    val page: Int,
    val results: List<UiUpComingMoviesResult>,
    val totalPages: Int
)
