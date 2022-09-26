package com.example.moviescity.di

import com.example.moviescity.modules.movies_activity.di.MoviesActivityFragmentsBuilderModule
import com.example.moviescity.modules.movies_activity.di.MoviesActivityScope
import com.example.moviescity.modules.movies_activity.presentation.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivitiesBuilderModule {
    @MoviesActivityScope
    @ContributesAndroidInjector(
        modules = [
            MoviesActivityFragmentsBuilderModule::class,
        ]
    )
    abstract fun getMoviesActivity(): MoviesActivity
}