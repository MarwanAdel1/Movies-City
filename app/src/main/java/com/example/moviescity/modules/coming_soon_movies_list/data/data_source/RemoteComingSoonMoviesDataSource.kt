package com.example.moviescity.modules.coming_soon_movies_list.data.data_source

import com.example.moviescity.modules.coming_soon_movies_list.data.model.ApiUpComingSoonMovieModel

interface RemoteComingSoonMoviesDataSource {
    suspend fun getComingSoonMovies(
        apiKey: String,
        language: String,
        page: Int
    ): ApiUpComingSoonMovieModel
}