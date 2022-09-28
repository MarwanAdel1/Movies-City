package com.example.moviescity.modules.coming_soon_movies_list.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescity.modules.coming_soon_movies_list.domain.use_case.GetUpcomingMovies
import com.example.moviescity.modules.coming_soon_movies_list.presentation.mapper.UiUpcomingMovieMapper
import com.example.moviescity.modules.coming_soon_movies_list.presentation.model.UiUpcomingMovieModel
import com.example.moviescity.utils.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpcomingMoviesViewModel @Inject constructor(
    private val getUpcomingMovies: GetUpcomingMovies,
    private val constants: Constants
) : ViewModel() {
    private val TAG = "UpcomingMoviesViewModel"

    private val _upcomingMovies = MutableLiveData<UiUpcomingMovieModel?>()
    val upcomingMovies: LiveData<UiUpcomingMovieModel?> = _upcomingMovies

    private val coroutineHandlerException =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "Error: ${throwable.message} ")
        }

    fun getUpcomingMovies() {
        viewModelScope.launch(coroutineHandlerException) {
            val response = getUpcomingMovies.execute(
                apiKey = constants.API_KEY,
                language = "en-US",
                page = 1
            )

            if (response != null) {
                val movies = UiUpcomingMovieMapper.toUiUpcomingMovieModel(
                    response
                )
                _upcomingMovies.postValue(movies)
            } else {
                _upcomingMovies.postValue(null)
            }
        }
    }
}