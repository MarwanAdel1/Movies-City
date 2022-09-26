package com.example.moviescity.modules.coming_soon_movies_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescity.modules.coming_soon_movies_list.domain.use_case.GetUpcomingMovies
import com.example.moviescity.modules.coming_soon_movies_list.presentation.mapper.UiUpcomingMovieMapper
import com.example.moviescity.modules.coming_soon_movies_list.presentation.model.UiUpcomingMovieModel
import com.example.moviescity.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpcomingMoviesViewModel @Inject constructor(
    private val getUpcomingMovies: GetUpcomingMovies,
    private val constants: Constants
) : ViewModel() {
    private val _upcomingMovies = MutableLiveData<UiUpcomingMovieModel>()
    val upcomingMovies: LiveData<UiUpcomingMovieModel> = _upcomingMovies


    fun getUpcomingMovies() {
        viewModelScope.launch {
            val movies = UiUpcomingMovieMapper.toUiUpcomingMovieModel(
                getUpcomingMovies.execute(
                    apiKey = constants.API_KEY,
                    language = "en-US",
                    page = 1
                )
            )

            _upcomingMovies.postValue(movies)
        }
    }
}