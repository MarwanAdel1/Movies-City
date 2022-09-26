package com.example.moviescity.modules.movies_list.domain.use_case

import com.example.moviescity.modules.movies_list.domain.repository.MoviesRepo
import javax.inject.Inject

class GetTopRatedMovies @Inject constructor(private val moviesRepo: MoviesRepo) {
    suspend fun execute(
        apiKey: String,
        language: String,
        page: Int
    ) = moviesRepo.getTopRatedMovies(
        apiKey = apiKey,
        language = language,
        page = page
    )
}