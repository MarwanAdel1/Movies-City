package com.example.moviescity.modules.coming_soon_movies_list.data.model

import com.squareup.moshi.Json

data class ApiUpComingSoonMoviesDates(
    @Json(name = "maximum")
    var maximum: String,
    @Json(name = "minimum")
    var minimum: String
)
