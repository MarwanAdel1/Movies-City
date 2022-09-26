package com.example.moviescity.modules.movies_list.presentation.mapper

import com.example.moviescity.modules.movies_list.domain.model.DomainMovieModel
import com.example.moviescity.modules.movies_list.presentation.model.ViewMovieModel
import com.example.moviescity.modules.movies_list.presentation.model.ViewMoviesResult

object UiMovieListMapper {
    fun toUiMovieModel(domainMovieModel: DomainMovieModel, paginated: Boolean): ViewMovieModel {
        val uiMoviesList = mutableListOf<ViewMoviesResult>()

        domainMovieModel.results.forEach {
            val movie = ViewMoviesResult(
                id = it.id,
                title = it.title,
                adult = it.adult,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage
            )

            uiMoviesList.add(movie)
        }

        return ViewMovieModel(
            paginated = paginated,
            movies = uiMoviesList
        )
    }
}