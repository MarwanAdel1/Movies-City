package com.example.moviescity.modules.movies_list.data.data_source.remote_sorce

import com.example.moviescity.modules.movies_list.data.data_source.RemoteMoviesListDataSource
import com.example.moviescity.modules.movies_list.data.model.ApiMovieModel
import com.example.moviescity.modules.movies_list.data.services.MoviesListService
import javax.inject.Inject

class RemoteMoviesListDataSourceImp @Inject constructor(private val moviesService: MoviesListService) :
    RemoteMoviesListDataSource {
    override suspend fun getDiscoverMovies(
        apiKey: String,
        language: String,
        includeVideo: Boolean,
        includeAdult: Boolean,
        page: Int
    ): ApiMovieModel {
        return moviesService.getAllMoviesByPreferences(
            apiKey = apiKey,
            language = language,
            includeVideo = includeVideo,
            includeAdult = includeAdult,
            page = page
        )
    }

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): ApiMovieModel {
        return moviesService.getPopularMovies(
            apiKey = apiKey,
            language = language,
            page = page
        )
    }

    override suspend fun getTopRatedMovies(
        apiKey: String,
        language: String,
        page: Int
    ): ApiMovieModel {
        return moviesService.getTopRatedMovies(
            apiKey = apiKey,
            language = language,
            page = page
        )
    }
}