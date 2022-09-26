package com.example.moviescity.modules.movies_list.di

import androidx.lifecycle.ViewModel
import com.example.moviescity.di.ViewModelKey
import com.example.moviescity.modules.movies_fragment.di.MoviesFragmentScope
import com.example.moviescity.modules.movies_list.presentation.viewmodel.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviesListViewModelModule {
    @MoviesFragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun bindMoviesListViewModelModule(moviesListViewModel: MoviesListViewModel): ViewModel
}