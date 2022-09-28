package com.example.moviescity.modules.coming_soon_movies_list.di

import com.example.moviescity.modules.coming_soon_movies_list.data.data_source.RemoteComingSoonMoviesDataSource
import com.example.moviescity.modules.coming_soon_movies_list.data.data_source.remote_source.RemoteComingSoonMoviesDataSourceImp
import com.example.moviescity.modules.coming_soon_movies_list.data.repository.UpcomingMoviesRepoImp
import com.example.moviescity.modules.coming_soon_movies_list.domain.repository.UpcomingMoviesRepo
import dagger.Binds
import dagger.Module

@Module
abstract class UpcomingMoviesListModule {
    @Binds
    abstract fun getUpcomingMoviesRepo(moviesRepoImp: UpcomingMoviesRepoImp): UpcomingMoviesRepo

    @Binds
    abstract fun getRemoteUpcomingMoviesListDataSource(moviesListDataSource: RemoteComingSoonMoviesDataSourceImp): RemoteComingSoonMoviesDataSource
}