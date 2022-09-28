package com.example.moviescity.modules.coming_soon_movies_list.data.data_source.remote_source

import com.example.moviescity.modules.coming_soon_movies_list.data.data_source.RemoteComingSoonMoviesDataSource
import com.example.moviescity.modules.coming_soon_movies_list.data.model.ApiUpComingSoonMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.data.services.ComingSoonMoviesServices
import com.example.moviescity.utils.NetworkResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteComingSoonMoviesDataSourceImp @Inject constructor(private val moviesService: ComingSoonMoviesServices) :
    RemoteComingSoonMoviesDataSource {
    override suspend fun getComingSoonMovies(
        apiKey: String,
        language: String,
        page: Int
    ): NetworkResponse<ApiUpComingSoonMovieModel> {
        return safeCall {
            moviesService.getUpcomingMovies(
                apiKey = apiKey,
                language = language,
                page = page
            )
        }
    }

    private inline fun <T> safeCall(apiCall: () -> Response<T>): NetworkResponse<T> {
        return try {
            NetworkResponse.success(apiCall.invoke())
        } catch (exception: Exception) {
            NetworkResponse.failure(exception)
        }
    }
}

