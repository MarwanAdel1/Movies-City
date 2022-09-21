package com.example.moviescity.di

import com.example.moviescity.modules.movies_activity.presentation.MoviesActivity
import com.example.moviescity.modules.movies_list.di.MoviesListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivitiesBuilderModule {
    @ContributesAndroidInjector(
        modules = [
            MoviesListModule::class
        ]
    )
    abstract fun getMoviesActivity(): MoviesActivity
}