package com.example.moviescity.modules.movies_list.data.services

import com.example.moviescity.modules.movies_list.data.model.ApiMovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesListService {
    @GET("/3/discover/movie")
    suspend fun getAllMoviesByPreferences(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("page") page: Int
    ): ApiMovieModel

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ApiMovieModel

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ApiMovieModel
}