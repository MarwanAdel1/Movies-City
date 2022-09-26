package com.example.moviescity.modules.movies_list.domain.repository

import com.example.moviescity.modules.coming_soon_movies_list.domain.model.DomainUpComingSoonMovieModel
import com.example.moviescity.modules.movies_list.domain.model.DomainMovieModel

interface MoviesRepo {
    suspend fun getDiscoverMovies(
        apiKey: String,
        language: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: Int
    ): DomainMovieModel

    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): DomainMovieModel

    suspend fun getTopRatedMovies(
        apiKey: String,
        language: String,
        page: Int
    ): DomainMovieModel
}