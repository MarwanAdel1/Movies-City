package com.example.moviescity.modules.movies_list.presentation.viewmodel

import android.util.Log
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
import com.example.moviescity.utils.NetworkConnectivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val getDiscoverMovies: GetDiscoverMovies,
    private val getPopularMovies: GetPopularMovies,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val constants: Constants,
    private val network: NetworkConnectivity
) : ViewModel() {
    private val TAG = "MoviesListViewModel"

    private val _page = MutableLiveData<Int>()

    private val _movies = MutableLiveData<ViewMovieModel?>()
    val movies: LiveData<ViewMovieModel?>
        get() = _movies

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean>
        get() = _isConnected

    private val coroutineHandlerException =
        CoroutineExceptionHandler { _, throwable ->
            Log.e(TAG, "Error: ${throwable.message} ")
        }

    init {
        _page.value = 0
    }

    fun getNextPage(type: EnumMoviesType, paginated: Boolean) {
        val page = if (paginated) {
            _page.value!! + 1
        } else {
            1
        }

        when (type) {
            EnumMoviesType.DISCOVER_MOVIES -> getDiscoverMovies(page, paginated)
            EnumMoviesType.POPULAR_MOVIES -> getPopularMovies(page, paginated)
            EnumMoviesType.TOP_RATED_MOVIES -> getTopRatedMovies(page, paginated)
        }
    }

    fun getDiscoverMovies(page: Int, paginated: Boolean) {
        viewModelScope.launch(coroutineHandlerException) {
            val status = network.checkConnectivity()
            _isConnected.postValue(status)

            val response = getDiscoverMovies.execute(
                apiKey = constants.API_KEY,
                language = "en-US",
                includeVideo = false,
                includeAdult = false,
                page = page
            )

            if (response != null) {
                val movies = UiMovieListMapper.toUiMovieModel(
                    paginated = paginated,
                    domainMovieModel = response
                )
                _movies.postValue(movies)
                _page.postValue(page)
            } else {
                _movies.postValue(null)
            }

        }
    }

    fun getPopularMovies(page: Int, paginated: Boolean) {
        viewModelScope.launch(coroutineHandlerException) {
            val status = network.checkConnectivity()
            _isConnected.postValue(status)

            val response = getPopularMovies.execute(
                apiKey = constants.API_KEY,
                language = "en-US",
                page = page
            )

            if (response != null) {
                val movies = UiMovieListMapper.toUiMovieModel(
                    paginated = paginated,
                    domainMovieModel = response
                )
                _movies.postValue(movies)
                _page.postValue(page)
            } else {
                _movies.postValue(null)
            }
        }
    }

    fun getTopRatedMovies(page: Int, paginated: Boolean) {
        viewModelScope.launch(coroutineHandlerException) {
            val status = network.checkConnectivity()
            _isConnected.postValue(status)
            val response = getTopRatedMovies.execute(
                apiKey = constants.API_KEY,
                language = "en-US",
                page = page
            )

            if (response != null) {
                val movies = UiMovieListMapper.toUiMovieModel(
                    paginated = paginated,
                    domainMovieModel = response
                )

                _movies.postValue(movies)
                _page.postValue(page)
            } else {
                _movies.postValue(null)
            }
        }
    }
}
