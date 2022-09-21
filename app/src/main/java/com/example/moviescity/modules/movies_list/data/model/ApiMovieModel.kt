package com.example.moviescity.modules.movies_list.data.model

import com.squareup.moshi.Json

data class ApiMovieModel(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<Results>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
