package com.example.moviescity.modules.movies_fragment.di

import com.example.moviescity.modules.coming_soon_movies_list.data.services.ComingSoonMoviesServices
import com.example.moviescity.modules.movies_fragment.presentation.view.MovieTypeClickListener
import com.example.moviescity.modules.movies_fragment.presentation.view.MoviesListFragment
import com.example.moviescity.modules.movies_list.data.services.MoviesListService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class MoviesFragmentModule {
    companion object {
        @MoviesFragmentScope
        @Provides
        fun provideMoviesAPI(retrofit: Retrofit): MoviesListService {
            return retrofit.create(MoviesListService::class.java)
        }

        @MoviesFragmentScope
        @Provides
        fun provideUpcomingMoviesAPI(retrofit: Retrofit): ComingSoonMoviesServices {
            return retrofit.create(ComingSoonMoviesServices::class.java)
        }
    }

    @MoviesFragmentScope
    @Binds
    abstract fun getFilterClickListener(fragment: MoviesListFragment): MovieTypeClickListener
}

