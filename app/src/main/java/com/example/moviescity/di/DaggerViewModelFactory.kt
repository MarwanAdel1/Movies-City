package com.example.moviescity.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviescity.modules.coming_soon_movies_list.presentation.viewmodel.UpcomingMoviesViewModel
import com.example.moviescity.modules.movies_list.presentation.viewmodel.MoviesListViewModel
import javax.inject.Inject

class DaggerViewModelFactory @Inject constructor(
    private val viewModelsMap: Map<Class<out ViewModel>,
            @JvmSuppressWildcards ViewModel>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            return viewModelsMap[MoviesListViewModel::class.java] as T
        } else if (modelClass.isAssignableFrom(UpcomingMoviesViewModel::class.java)) {
            return viewModelsMap[UpcomingMoviesViewModel::class.java] as T
        }

        throw IllegalArgumentException("Unknown model class")
    }
}
