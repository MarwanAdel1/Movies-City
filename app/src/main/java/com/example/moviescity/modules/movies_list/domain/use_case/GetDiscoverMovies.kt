package com.example.moviescity.modules.movies_list.domain.use_case

import com.example.moviescity.modules.movies_list.domain.repository.MoviesRepo
import javax.inject.Inject

class GetDiscoverMovies @Inject constructor(private val moviesRepo: MoviesRepo) {
    suspend fun execute(
        apiKey: String,
        language: String,
        includeVideo: Boolean,
        includeAdult: Boolean,
        page: Int
    ) = moviesRepo.getDiscoverMovies(
        apiKey = apiKey,
        language = language,
        includeVideo = includeVideo,
        includeAdult = includeAdult,
        page = page
    )
}