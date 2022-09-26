package com.example.moviescity.modules.movies_list.data.data_source

import com.example.moviescity.modules.movies_list.data.model.ApiMovieModel
import com.example.moviescity.modules.movies_list.data.model.ApiMoviesResult

interface LocalMoviesListDataSource /*: RemoteMoviesListDataSource*/ {
    suspend fun getDiscoverMovies(
        language: String,
    ): ApiMovieModel

    suspend fun getPopularMovies(
        language: String,
    ): ApiMovieModel

    suspend fun getTopRatedMovies(
        language: String
    ): ApiMovieModel


    suspend fun saveDiscoverMoviesToDB(
        language: String,
        movie: ApiMoviesResult
    )

    suspend fun savePopularMoviesToDB(
        language: String,
        movie: ApiMoviesResult
    )

    suspend fun saveTopRatedMoviesToDB(
        language: String,
        movie: ApiMoviesResult
    )

    suspend fun addMovieToFavourite(
        language: String,
        movie: ApiMoviesResult
    )


}