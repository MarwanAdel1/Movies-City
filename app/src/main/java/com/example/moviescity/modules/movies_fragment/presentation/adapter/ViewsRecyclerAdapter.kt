package com.example.moviescity.modules.movies_fragment.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescity.R
import com.example.moviescity.databinding.ViewMoviesFragmentMoviesListBinding
import com.example.moviescity.databinding.ViewMoviesTitleFragmentMoviesListBinding
import com.example.moviescity.databinding.ViewUpcomingMoviesFragmentMoviesListBinding
import com.example.moviescity.modules.coming_soon_movies_list.presentation.adapter.ComingSoonMoviesListAdapter
import com.example.moviescity.modules.coming_soon_movies_list.presentation.model.AdapterComingSoonMovieModel
import com.example.moviescity.modules.movies_fragment.presentation.view.MovieTypeClickListener
import com.example.moviescity.modules.movies_list.presentation.model.AdapterMovieModel
import com.example.moviescity.modules.movies_list.presentation.view.MovieClickListener
import com.example.moviescity.utils.Constants
import com.squareup.picasso.Picasso
import javax.inject.Inject


class ViewsRecyclerAdapter @Inject constructor(
    private val movieClickCallback: MovieClickListener,
    private val movieTypeCallback: MovieTypeClickListener,
    private val picasso: Picasso,
    private val constants: Constants
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    @Inject
    lateinit var upcomingMoviesAdapter: ComingSoonMoviesListAdapter

    private var upcomingMovies = mutableListOf<AdapterComingSoonMovieModel>()
    private var movies = mutableListOf<AdapterMovieModel>()
    private var moviesTypeTxt = ""

    fun updateUpcomingMovies(upcomingMovies: List<AdapterComingSoonMovieModel>) {
        this.upcomingMovies = upcomingMovies.toMutableList()
        notifyDataSetChanged()
    }

    fun updateMovies(movies: List<AdapterMovieModel>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun getLatestUpdatedMovies(): List<AdapterMovieModel> {
        return movies
    }

    fun updateFilterText(value: String) {
        moviesTypeTxt = value
        if (upcomingMovies.isEmpty()) {
            notifyItemChanged(0)
        } else {
            notifyItemChanged(1)
        }

    }


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> if (upcomingMovies.isEmpty()) {
                1
            } else {
                0
            }
            1 -> if (upcomingMovies.isEmpty()) {
                2
            } else {
                1
            }
            else -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            0 -> return UpcomingMoviesViewHolder(
                ViewUpcomingMoviesFragmentMoviesListBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            1 -> return MoviesTitleViewHolder(
                ViewMoviesTitleFragmentMoviesListBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            2 -> return MoviesViewHolder(
                ViewMoviesFragmentMoviesListBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UpcomingMoviesViewHolder -> holder.bind(upcomingMovies)
            is MoviesTitleViewHolder -> holder.bind(moviesTypeTxt)
            is MoviesViewHolder -> if (upcomingMovies.isEmpty()) {
                holder.bind(movies[position - 1])
            } else {
                holder.bind(movies[position - 2])
            }
        }
    }

    override fun getItemCount(): Int {
        return if (upcomingMovies.isNotEmpty()) {
            (movies.size + 2)
        } else {
            (movies.size + 1)
        }

    }


    inner class MoviesViewHolder(private val binding: ViewMoviesFragmentMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: AdapterMovieModel) {
            binding.apply {
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

            itemView.setOnClickListener {

            }
        }
    }

    inner class UpcomingMoviesViewHolder(private val binding: ViewUpcomingMoviesFragmentMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: List<AdapterComingSoonMovieModel>) {
            binding.comingSoonRecycler.adapter = upcomingMoviesAdapter
            upcomingMoviesAdapter.setMovies(movies)
        }
    }

    inner class MoviesTitleViewHolder(private val binding: ViewMoviesTitleFragmentMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String) {
            binding.filterImageButton.setOnClickListener {
                movieTypeCallback.onFilterSelected(it)
            }

            binding.moviesFilterTextview.text = value
        }
    }
}
