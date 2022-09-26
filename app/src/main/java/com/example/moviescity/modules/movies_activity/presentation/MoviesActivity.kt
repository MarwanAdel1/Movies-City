package com.example.moviescity.modules.movies_activity.presentation

import android.os.Bundle
import com.example.moviescity.R
import dagger.android.support.DaggerAppCompatActivity

class MoviesActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}