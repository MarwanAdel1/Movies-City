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
    ): DomainMovieModel? {
        val discoverMoviesRequest = remoteSource.getDiscoverMovies(
            apiKey = apiKey,
            language = language,
            includeAdult = includeAdult,
            includeVideo = includeVideo,
            page = page
        )

        if (discoverMoviesRequest.isFailed) { //out of control error
            return null
        }

        if (!discoverMoviesRequest.isSuccessful) { //api error
            return null
        }

        return MoviesMapper.toDomainMovieModel(discoverMoviesRequest.body)
    }

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): DomainMovieModel? {
        val popularMoviesRequest = remoteSource.getPopularMovies(
            apiKey = apiKey,
            language = language,
            page = page
        )

        if (popularMoviesRequest.isFailed) {
            return null
        }

        if (!popularMoviesRequest.isSuccessful) {
            return null
        }

        return MoviesMapper.toDomainMovieModel(popularMoviesRequest.body)
    }

    override suspend fun getTopRatedMovies(
        apiKey: String,
        language: String,
        page: Int
    ): DomainMovieModel? {
        val topRatedMoviesRequest = remoteSource.getTopRatedMovies(
            apiKey = apiKey,
            language = language,
            page = page
        )

        if (topRatedMoviesRequest.isFailed) {
            return null
        }

        if (!topRatedMoviesRequest.isSuccessful) {
            return null
        }

        return MoviesMapper.toDomainMovieModel(topRatedMoviesRequest.body)
    }
}