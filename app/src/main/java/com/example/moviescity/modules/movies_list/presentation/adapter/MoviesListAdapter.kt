package com.example.moviescity.modules.movies_list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescity.R
import com.example.moviescity.databinding.ViewMoviesFragmentMoviesListBinding
import com.example.moviescity.modules.movies_list.presentation.model.AdapterMovieModel
import com.example.moviescity.modules.movies_list.presentation.view.MovieClickListener
import com.example.moviescity.utils.Constants
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MoviesListAdapter @Inject constructor(
    private val communicator: MovieClickListener,
    private val picasso: Picasso,
    private val constants: Constants
) : RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {
    private val TAG = "MoviesListAdapter"

    private lateinit var movies: List<AdapterMovieModel>

    fun setMovies(movies: List<AdapterMovieModel>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesListViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = ViewMoviesFragmentMoviesListBinding.inflate(layoutInflater, parent, false)

        return MoviesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.apply {
            itemMovieImage.clipToOutline = true

            picasso
                .load("${constants.API_IMAGE_BASE_URL}${movie.posterPath}")
                .placeholder(R.drawable.image_default_movie_poster)
                .error(R.drawable.image_default_movie_poster)
                .into(itemMovieImage)

            if (movie.adult) {
                itemMovieAdultImageview.visibility = View.VISIBLE
            } else {
                itemMovieAdultImageview.visibility = View.INVISIBLE
            }

            itemMovieName.text = movie.title
        }

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return if (::movies.isInitialized) {
            movies.size
        } else {
            0
        }
    }

    inner class MoviesListViewHolder(val binding: ViewMoviesFragmentMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root)
}