package com.example.moviescity.modules.coming_soon_movies_list.presentation.mapper

import com.example.moviescity.modules.coming_soon_movies_list.domain.model.DomainUpComingSoonMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.presentation.model.UiUpComingMoviesResult
import com.example.moviescity.modules.coming_soon_movies_list.presentation.model.UiUpcomingMovieModel

object UiUpcomingMovieMapper {
    fun toUiUpcomingMovieModel(domainUpComingSoonMovieModel: DomainUpComingSoonMovieModel): UiUpcomingMovieModel {
        val results = mutableListOf<UiUpComingMoviesResult>()

        domainUpComingSoonMovieModel.results.forEach {
            val result = UiUpComingMoviesResult(
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                adult = it.adult,
                title = it.title,
                id = it.id
            )

            results.add(result)
        }

        return UiUpcomingMovieModel(
            page = domainUpComingSoonMovieModel.page,
            totalPages = domainUpComingSoonMovieModel.totalPages,
            results = results
        )
    }
}