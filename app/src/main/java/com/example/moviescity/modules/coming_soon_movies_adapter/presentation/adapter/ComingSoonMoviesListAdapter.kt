package com.example.moviescity.modules.coming_soon_movies_adapter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescity.R
import com.example.moviescity.databinding.ItemMovieComingSoonBinding
import com.example.moviescity.modules.coming_soon_movies_adapter.presentation.model.AdapterComingSoonMovieModel
import com.example.moviescity.modules.movies_list_adapter.presentation.view.MovieClickListener
import com.example.moviescity.utils.Constants
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ComingSoonMoviesListAdapter @Inject constructor(
    private val communicator: MovieClickListener,
    private val picasso: Picasso,
    private val constants: Constants
) : RecyclerView.Adapter<ComingSoonMoviesListAdapter.MoviesListViewHolder>() {

    private lateinit var movies: List<AdapterComingSoonMovieModel>

    fun setMovies(movies: List<AdapterComingSoonMovieModel>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesListViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = ItemMovieComingSoonBinding.inflate(layoutInflater, parent, false)

        return MoviesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.apply {
            comingSoonMovieImageview.clipToOutline = true

            picasso
                .load("${constants.API_IMAGE_BASE_URL}${movie.posterPath}")
                .placeholder(R.drawable.image_default_movie_poster)
                .error(R.drawable.image_default_movie_poster)
                .into(comingSoonMovieImageview)

            comingSoonMovieTitleTextview.text = movie.title
            comingSoonMovieReleaseDateTextview.text = movie.releaseDate
        }

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        var size = 0
        if (::movies.isInitialized) {
            size = movies.size
        }
        return size
    }

    inner class MoviesListViewHolder(val binding: ItemMovieComingSoonBinding) :
        RecyclerView.ViewHolder(binding.root)
}