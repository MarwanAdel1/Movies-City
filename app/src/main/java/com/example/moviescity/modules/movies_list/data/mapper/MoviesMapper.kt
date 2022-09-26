package com.example.moviescity.modules.movies_list.data.mapper

import com.example.moviescity.modules.movies_list.data.model.ApiMovieModel
import com.example.moviescity.modules.movies_list.domain.model.DomainMovieModel
import com.example.moviescity.modules.movies_list.domain.model.DomainMoviesResult

object MoviesMapper {
    fun toDomainMovieModel(apiMovieModel: ApiMovieModel): DomainMovieModel {
        val domainMoviesResult = mutableListOf<DomainMoviesResult>()

        apiMovieModel.results.forEach {
            val movieResult = DomainMoviesResult(
                id = it.id,
                adult = it.adult,
                posterPath = it.posterPath,
                title = it.title,
                voteAverage = it.voteAverage
            )

            domainMoviesResult.add(movieResult)
        }

        return DomainMovieModel(results = domainMoviesResult)
    }
}