package com.example.moviescity.modules.coming_soon_movies_list.data.mapper

import com.example.moviescity.modules.coming_soon_movies_list.data.model.ApiUpComingSoonMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.domain.model.DomainUpComingSoonMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.domain.model.DomainUpComingSoonMoviesResult

object ApiUpcomingMoviesMapper {
    fun toDomainUpcomingMovieModel(apiUpComingSoonMovieModel: ApiUpComingSoonMovieModel): DomainUpComingSoonMovieModel {
        val results = mutableListOf<DomainUpComingSoonMoviesResult>()

        apiUpComingSoonMovieModel.results.forEach {
            val domainUpComingSoonMoviesResult = DomainUpComingSoonMoviesResult(
                id = it.id,
                title = it.title,
                adult = it.adult,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage
            )

            results.add(domainUpComingSoonMoviesResult)
        }

        return DomainUpComingSoonMovieModel(
            page = apiUpComingSoonMovieModel.page,
            totalPages = apiUpComingSoonMovieModel.totalPages,
            results = results
        )
    }
}