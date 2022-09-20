package com.example.moviescity.di

import com.example.moviescity.modules.movies_activity.presentation.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivitiesBuilderModule {
    @ContributesAndroidInjector
    abstract fun getMoviesActivity(): MoviesActivity
}