package com.example.moviescity.modules.movies_activity.presentation

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.moviescity.R
import com.example.moviescity.modules.movies_list.data.movies_api.MoviesListService
import com.example.moviescity.utils.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var apiService: MoviesListService

    @Inject
    lateinit var constants: Constants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            apiService.getAllMoviesByPreferences(
                apiKey = "c7f3a3d043c877431add5c1dc2df75c8",
                language = "en-US",
                includeAdult = false,
                includeVideo = false,
                page = 2,
                sortBy = "top_rated.desc",
            )
        }

    }
}