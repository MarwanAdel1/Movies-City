package com.example.moviescity.modules.movies_list.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescity.modules.movies_list.domain.use_case.GetDiscoverMovies
import com.example.moviescity.modules.movies_list.domain.use_case.GetPopularMovies
import com.example.moviescity.modules.movies_list.domain.use_case.GetTopRatedMovies
import com.example.moviescity.modules.movies_list.presentation.mapper.UiMovieListMapper
import com.example.moviescity.modules.movies_list.presentation.model.EnumMoviesType
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
    private val _page = MutableLiveData<Int>()

    private val _movies = MutableLiveData<ViewMovieModel>()
    val movies: LiveData<ViewMovieModel> = _movies

    init {
        _page.value = 0
    }

    fun getNextPage(type: EnumMoviesType, paginated: Boolean) {
        val page = if (paginated) {
            _page.value!! + 1
        } else {
            1
        }
        _page.postValue(page)
        when (type) {
            EnumMoviesType.DISCOVER_MOVIES -> getDiscoverMovies(page, paginated)
            EnumMoviesType.POPULAR_MOVIES -> getPopularMovies(page, paginated)
            EnumMoviesType.TOP_RATED_MOVIES -> getTopRatedMovies(page, paginated)
        }
    }

    fun getDiscoverMovies(page: Int, paginated: Boolean) {
        viewModelScope.launch {
            val movies = UiMovieListMapper.toUiMovieModel(
                paginated = paginated,
                domainMovieModel = getDiscoverMovies.execute(
                    apiKey = constants.API_KEY,
                    language = "en-US",
                    includeVideo = false,
                    includeAdult = false,
                    page = page
                )
            )

            _movies.postValue(movies)
        }
    }

    fun getPopularMovies(page: Int, paginated: Boolean) {
        viewModelScope.launch {
            val movies = UiMovieListMapper.toUiMovieModel(
                paginated = paginated,
                domainMovieModel = getPopularMovies.execute(
                    apiKey = constants.API_KEY,
                    language = "en-US",
                    page = page
                )
            )

            _movies.postValue(movies)
        }
    }

    fun getTopRatedMovies(page: Int, paginated: Boolean) {
        viewModelScope.launch {
            val movies = UiMovieListMapper.toUiMovieModel(
                paginated = paginated,
                domainMovieModel = getTopRatedMovies.execute(
                    apiKey = constants.API_KEY,
                    language = "en-US",
                    page = page
                )
            )

            _movies.postValue(movies)
        }
    }
}