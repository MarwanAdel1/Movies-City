package com.example.moviescity.modules.movies_list.data.data_source

import com.example.moviescity.modules.movies_list.data.model.ApiMovieModel

interface RemoteMoviesListDataSource {
    suspend fun getDiscoverMovies(
        apiKey: String,
        language: String,
        includeVideo: Boolean,
        includeAdult: Boolean,
        page: Int
    ): ApiMovieModel

    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): ApiMovieModel

    suspend fun getTopRatedMovies(
        apiKey: String,
        language: String,
        page: Int
    ): ApiMovieModel
}