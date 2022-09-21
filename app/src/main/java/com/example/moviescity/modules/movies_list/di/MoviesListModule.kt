package com.example.moviescity.modules.movies_list.di

import com.example.moviescity.modules.movies_list.data.movies_api.MoviesListService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MoviesListModule {
    @Provides
    fun provideMoviesAPI(retrofit: Retrofit): MoviesListService {
        return retrofit.create(MoviesListService::class.java)
    }
}