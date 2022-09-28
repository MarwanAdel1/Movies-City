package com.example.moviescity.modules.coming_soon_movies_list.data.data_source

import com.example.moviescity.modules.coming_soon_movies_list.data.model.ApiUpComingSoonMovieModel
import com.example.moviescity.utils.NetworkResponse

interface RemoteComingSoonMoviesDataSource {
    suspend fun getComingSoonMovies(
        apiKey: String,
        language: String,
        page: Int
    ): NetworkResponse<ApiUpComingSoonMovieModel>
}