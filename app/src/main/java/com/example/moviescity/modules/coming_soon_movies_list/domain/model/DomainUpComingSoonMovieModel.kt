package com.example.moviescity.modules.coming_soon_movies_list.domain.model

data class DomainUpComingSoonMovieModel(
    val page: Int,
    val results: List<DomainUpComingSoonMoviesResult>,
    val totalPages: Int
)
