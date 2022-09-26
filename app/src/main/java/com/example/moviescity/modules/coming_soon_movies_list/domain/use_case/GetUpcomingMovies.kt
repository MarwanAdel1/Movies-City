package com.example.moviescity.modules.coming_soon_movies_list.domain.use_case

import com.example.moviescity.modules.coming_soon_movies_list.domain.repository.UpcomingMoviesRepo
import com.example.moviescity.modules.movies_list.domain.repository.MoviesRepo
import javax.inject.Inject

class GetUpcomingMovies @Inject constructor(private val moviesRepo: UpcomingMoviesRepo) {
    suspend fun execute(
        apiKey: String,
        language: String,
        page: Int
    ) = moviesRepo.getUpcomingMovies(
        apiKey = apiKey,
        language = language,
        page = page
    )
}