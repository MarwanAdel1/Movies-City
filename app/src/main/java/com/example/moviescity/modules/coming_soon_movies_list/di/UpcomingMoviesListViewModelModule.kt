package com.example.moviescity.modules.coming_soon_movies_list.di

import androidx.lifecycle.ViewModel
import com.example.moviescity.di.ViewModelKey
import com.example.moviescity.modules.coming_soon_movies_list.presentation.viewmodel.UpcomingMoviesViewModel
import com.example.moviescity.modules.movies_fragment.di.MoviesFragmentScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UpcomingMoviesListViewModelModule {
    @MoviesFragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(UpcomingMoviesViewModel::class)
    abstract fun bindUpcomingMoviesListViewModelModule(moviesListViewModel: UpcomingMoviesViewModel): ViewModel
}