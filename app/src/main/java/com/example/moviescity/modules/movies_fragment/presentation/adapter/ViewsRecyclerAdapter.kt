package com.example.moviescity.modules.movies_fragment.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescity.databinding.ViewMoviesFragmentMoviesListBinding
import com.example.moviescity.databinding.ViewMoviesTitleFragmentMoviesListBinding
import com.example.moviescity.databinding.ViewUpcomingMoviesFragmentMoviesListBinding
import com.example.moviescity.modules.coming_soon_movies_adapter.presentation.adapter.ComingSoonMoviesListAdapter
import com.example.moviescity.modules.coming_soon_movies_adapter.presentation.model.AdapterComingSoonMovieModel
import com.example.moviescity.modules.movies_list_adapter.presentation.adapter.MoviesListAdapter
import com.example.moviescity.modules.movies_list_adapter.presentation.model.AdapterMovieModel
import javax.inject.Inject


class ViewsRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    @Inject
    lateinit var moviesListAdapter: MoviesListAdapter

    @Inject
    lateinit var upcomingMoviesAdapter: ComingSoonMoviesListAdapter

    private var upcomingMovies = mutableListOf<AdapterComingSoonMovieModel>()
    private var movies = mutableListOf<AdapterMovieModel>()

    fun updateUpcomingMovies(upcomingMovies: List<AdapterComingSoonMovieModel>) {
        this.upcomingMovies = upcomingMovies.toMutableList()
        notifyItemChanged(0)
    }

    fun updateMovies(movies: List<AdapterMovieModel>) {
        this.movies = movies.toMutableList()
        notifyItemChanged(1)
    }


    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> return 0
            1 -> return 1
            else -> return 2
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
            1 -> return
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
            is MoviesViewHolder -> holder.bind(movies)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }


    inner class MoviesViewHolder(private val binding: ViewMoviesFragmentMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movies: List<AdapterMovieModel>) {
            binding.moviesRecycler.adapter = moviesListAdapter
            moviesListAdapter.setMovies(movies)
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
        fun bind() {

        }
    }
}
