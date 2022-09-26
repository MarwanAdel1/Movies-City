package com.example.moviescity.modules.coming_soon_movies_list.data.model

import com.squareup.moshi.Json

data class ApiUpComingSoonMovieModel(
    @Json(name = "dates")
    val dates: ApiUpComingSoonMoviesDates,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<ApiUpComingSoonMoviesResult>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
