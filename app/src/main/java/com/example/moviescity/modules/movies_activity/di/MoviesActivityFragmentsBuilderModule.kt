package com.example.moviescity.modules.movies_activity.di

import com.example.moviescity.modules.coming_soon_movies_list.di.UpcomingMoviesListModule
import com.example.moviescity.modules.coming_soon_movies_list.di.UpcomingMoviesListViewModelModule
import com.example.moviescity.modules.movies_fragment.di.MoviesFragmentModule
import com.example.moviescity.modules.movies_fragment.di.MoviesFragmentScope
import com.example.moviescity.modules.movies_fragment.presentation.view.MoviesListFragment
import com.example.moviescity.modules.movies_list.di.MoviesListModule
import com.example.moviescity.modules.movies_list.di.MoviesListViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesActivityFragmentsBuilderModule {
    @MoviesFragmentScope
    @ContributesAndroidInjector(
        modules = [
            MoviesFragmentModule::class,
            MoviesListModule::class,
            MoviesListViewModelModule::class,
            UpcomingMoviesListModule::class,
            UpcomingMoviesListViewModelModule::class
        ]
    )
    abstract fun getMoviesListFragment(): MoviesListFragment
}
