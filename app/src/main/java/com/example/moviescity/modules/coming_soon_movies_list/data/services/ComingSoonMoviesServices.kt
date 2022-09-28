package com.example.moviescity.modules.coming_soon_movies_list.data.services

import com.example.moviescity.modules.coming_soon_movies_list.data.model.ApiUpComingSoonMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ComingSoonMoviesServices {
    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ApiUpComingSoonMovieModel>
}
