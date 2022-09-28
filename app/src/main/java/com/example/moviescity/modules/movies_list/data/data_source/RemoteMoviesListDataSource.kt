package com.example.moviescity.modules.movies_list.data.data_source

import com.example.moviescity.modules.movies_list.data.model.ApiMovieModel
import com.example.moviescity.utils.NetworkResponse

interface RemoteMoviesListDataSource {
    suspend fun getDiscoverMovies(
        apiKey: String,
        language: String,
        includeVideo: Boolean,
        includeAdult: Boolean,
        page: Int
    ): NetworkResponse<ApiMovieModel>

    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): NetworkResponse<ApiMovieModel>

    suspend fun getTopRatedMovies(
        apiKey: String,
        language: String,
        page: Int
    ): NetworkResponse<ApiMovieModel>
}