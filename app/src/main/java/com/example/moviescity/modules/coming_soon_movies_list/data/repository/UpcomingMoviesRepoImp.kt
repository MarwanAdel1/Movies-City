package com.example.moviescity.modules.coming_soon_movies_list.data.repository

import com.example.moviescity.modules.coming_soon_movies_list.data.data_source.RemoteComingSoonMoviesDataSource
import com.example.moviescity.modules.coming_soon_movies_list.data.mapper.ApiUpcomingMoviesMapper
import com.example.moviescity.modules.coming_soon_movies_list.domain.model.DomainUpComingSoonMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.domain.repository.UpcomingMoviesRepo
import javax.inject.Inject

class UpcomingMoviesRepoImp @Inject constructor(private val remoteSource: RemoteComingSoonMoviesDataSource) :
    UpcomingMoviesRepo {
    override suspend fun getUpcomingMovies(
        apiKey: String,
        language: String,
        page: Int
    ): DomainUpComingSoonMovieModel? {
        val upcomingMoviesRequest = remoteSource.getComingSoonMovies(
            apiKey = apiKey,
            language = language,
            page = page
        )

        if (upcomingMoviesRequest.isFailed) {
            return null
        }

        if (!upcomingMoviesRequest.isSuccessful) {
            return null
        }

        return ApiUpcomingMoviesMapper.toDomainUpcomingMovieModel(upcomingMoviesRequest.body)
    }
}