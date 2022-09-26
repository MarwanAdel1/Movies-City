package com.example.moviescity.modules.movies_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescity.modules.movies_list.domain.use_case.GetDiscoverMovies
import com.example.moviescity.modules.movies_list.domain.use_case.GetPopularMovies
import com.example.moviescity.modules.movies_list.domain.use_case.GetTopRatedMovies
import com.example.moviescity.modules.movies_list.presentation.mapper.UiMovieListMapper
import com.example.moviescity.modules.movies_list.presentation.model.ViewMovieModel
import com.example.moviescity.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val getDiscoverMovies: GetDiscoverMovies,
    private val getPopularMovies: GetPopularMovies,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val constants: Constants
) : ViewModel() {
    private val _discoverMovies = MutableLiveData<ViewMovieModel>()
    val discoverMovies: LiveData<ViewMovieModel> = _discoverMovies


    fun getDiscoverMovies() {
        viewModelScope.launch {
            val movies = UiMovieListMapper.toUiMovieModel(
                getDiscoverMovies.execute(
                    apiKey = constants.API_KEY,
                    language = "en-US",
                    includeVideo = false,
                    includeAdult = true,
                    page = 1
                )
            )

            _discoverMovies.postValue(movies)
        }
    }
}