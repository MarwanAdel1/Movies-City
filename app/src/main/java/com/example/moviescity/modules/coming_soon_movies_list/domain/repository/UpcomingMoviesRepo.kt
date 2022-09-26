package com.example.moviescity.modules.coming_soon_movies_list.domain.repository

import com.example.moviescity.modules.coming_soon_movies_list.domain.model.DomainUpComingSoonMovieModel

interface UpcomingMoviesRepo {
    suspend fun getUpcomingMovies(
        apiKey: String,
        language: String,
        page: Int
    ): DomainUpComingSoonMovieModel
}