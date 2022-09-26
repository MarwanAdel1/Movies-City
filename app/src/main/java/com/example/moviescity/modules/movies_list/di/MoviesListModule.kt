package com.example.moviescity.modules.movies_list.di

import com.example.moviescity.modules.movies_fragment.di.MoviesFragmentScope
import com.example.moviescity.modules.movies_fragment.presentation.view.MoviesListFragment
import com.example.moviescity.modules.movies_list.data.data_source.RemoteMoviesListDataSource
import com.example.moviescity.modules.movies_list.data.data_source.remote_sorce.RemoteMoviesListDataSourceImp
import com.example.moviescity.modules.movies_list.data.repository.MoviesRepoImp
import com.example.moviescity.modules.movies_list.domain.repository.MoviesRepo
import com.example.moviescity.modules.movies_list_adapter.presentation.view.MovieClickListener
import dagger.Binds
import dagger.Module

@Module
abstract class MoviesListModule {
    @MoviesFragmentScope
    @Binds
    abstract fun getMoviesRepo(moviesRepoImp: MoviesRepoImp): MoviesRepo

    @MoviesFragmentScope
    @Binds
    abstract fun getRemoteMoviesListDataSource(moviesListDataSource: RemoteMoviesListDataSourceImp): RemoteMoviesListDataSource

    @MoviesFragmentScope
    @Binds
    abstract fun getMovieClickListener(fragment: MoviesListFragment): MovieClickListener
}