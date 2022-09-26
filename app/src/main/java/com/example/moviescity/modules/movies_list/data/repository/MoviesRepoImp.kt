package com.example.moviescity.modules.movies_list.data.repository

import com.example.moviescity.modules.movies_list.data.data_source.RemoteMoviesListDataSource
import com.example.moviescity.modules.movies_list.data.mapper.MoviesMapper
import com.example.moviescity.modules.movies_list.domain.model.DomainMovieModel
import com.example.moviescity.modules.movies_list.domain.repository.MoviesRepo
import javax.inject.Inject

class MoviesRepoImp @Inject constructor(private val remoteSource: RemoteMoviesListDataSource) :
    MoviesRepo {

    override suspend fun getDiscoverMovies(
        apiKey: String,
        language: String,
        includeAdult: Boolean,
        includeVideo: Boolean,
        page: Int
    ): DomainMovieModel {
        val discoverMovies = remoteSource.getDiscoverMovies(
            apiKey = apiKey,
            language = language,
            includeAdult = includeAdult,
            includeVideo = includeVideo,
            page = page
        )

        return MoviesMapper.toDomainMovieModel(discoverMovies)
    }

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): DomainMovieModel {
        val popularMovies = remoteSource.getPopularMovies(
            apiKey = apiKey,
            language = language,
            page = page
        )

        return MoviesMapper.toDomainMovieModel(popularMovies)
    }

    override suspend fun getTopRatedMovies(
        apiKey: String,
        language: String,
        page: Int
    ): DomainMovieModel {
        val topRatedMovies = remoteSource.getTopRatedMovies(
            apiKey = apiKey,
            language = language,
            page = page
        )

        return MoviesMapper.toDomainMovieModel(topRatedMovies)
    }
}