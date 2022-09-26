package com.example.moviescity.modules.movies_list.presentation.model

data class ViewMovieModel(
    val paginated: Boolean,
    val movies: List<ViewMoviesResult>
)