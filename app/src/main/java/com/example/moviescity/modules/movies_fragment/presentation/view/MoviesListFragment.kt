package com.example.moviescity.modules.movies_fragment.presentation.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.moviescity.R
import com.example.moviescity.databinding.FragmentMoviesListBinding
import com.example.moviescity.modules.coming_soon_movies_adapter.presentation.model.AdapterComingSoonMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.presentation.model.UiUpcomingMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.presentation.viewmodel.UpcomingMoviesViewModel
import com.example.moviescity.modules.movies_fragment.presentation.adapter.ViewsRecyclerAdapter
import com.example.moviescity.modules.movies_list.presentation.model.ViewMovieModel
import com.example.moviescity.modules.movies_list.presentation.viewmodel.MoviesListViewModel
import com.example.moviescity.modules.movies_list_adapter.presentation.model.AdapterMovieModel
import com.example.moviescity.modules.movies_list_adapter.presentation.view.MovieClickListener
import com.example.moviescity.utils.DaggerViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MoviesListFragment : DaggerFragment(), MovieClickListener {
    private lateinit var binding: FragmentMoviesListBinding

    @Inject
    lateinit var viewsRecyclerAdapter: ViewsRecyclerAdapter

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private lateinit var moviesListViewModel: MoviesListViewModel
    private lateinit var upComingSoonMoviesListViewModel: UpcomingMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesListViewModel =
            ViewModelProvider(this, daggerViewModelFactory)[MoviesListViewModel::class.java]

        upComingSoonMoviesListViewModel =
            ViewModelProvider(this, daggerViewModelFactory)[UpcomingMoviesViewModel::class.java]

        binding.searchImageButton.setOnClickListener {
            showSearchBar()
        }

        binding.searchEdittext.setOnEditorActionListener(OnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideSearchBar()
                view.hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })

        binding.searchEdittext.setOnClickListener {
            hideSearchBar()
            it.hideKeyboard()
        }

        binding.viewsRecyclerview.adapter = viewsRecyclerAdapter
        (binding.viewsRecyclerview.layoutManager as GridLayoutManager).spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) {
                    2
                } else {
                    1
                }
            }
        }


        moviesListViewModel.getDiscoverMovies()
        moviesListViewModel.discoverMovies.observe(viewLifecycleOwner) {
            viewsRecyclerAdapter.updateMovies(moviesToAdapterModel(it))
        }

        upComingSoonMoviesListViewModel.getUpcomingMovies()
        upComingSoonMoviesListViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            viewsRecyclerAdapter.updateUpcomingMovies(upcomingMoviesToAdapterModel(it))
        }
    }

    private fun showSearchBar() {
        val slideOutUpAnim = AnimationUtils.loadAnimation(context, R.anim.slide_out_up_anim)
        binding.searchImageButton.startAnimation(slideOutUpAnim)
        binding.appNameTextview.startAnimation(slideOutUpAnim)
        binding.navigationMenuImageButton.startAnimation(slideOutUpAnim)

        binding.searchImageButton.visibility = View.INVISIBLE
        binding.appNameTextview.visibility = View.INVISIBLE
        binding.navigationMenuImageButton.visibility = View.INVISIBLE

        val fadeInAnim = AnimationUtils.loadAnimation(context, R.anim.fade_in_anim)
        binding.searchEdittext.visibility = View.VISIBLE

        binding.searchEdittext.startAnimation(fadeInAnim)
    }

    private fun hideSearchBar() {
        val slideOutUpAnim = AnimationUtils.loadAnimation(context, R.anim.slide_out_up_anim)
        binding.searchEdittext.startAnimation(slideOutUpAnim)
        binding.searchEdittext.visibility = View.INVISIBLE

        binding.appNameTextview.visibility = View.VISIBLE
        binding.navigationMenuImageButton.visibility = View.VISIBLE
        binding.searchImageButton.visibility = View.VISIBLE

        binding.searchEdittext.text.clear()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun moviesToAdapterModel(moviesList: ViewMovieModel): List<AdapterMovieModel> {
        val adapterMovieList = mutableListOf<AdapterMovieModel>()

        moviesList.movies.forEach {
            val movie = AdapterMovieModel(
                id = it.id,
                adult = it.adult,
                title = it.title,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage
            )

            adapterMovieList.add(movie)
        }

        return adapterMovieList
    }

    private fun upcomingMoviesToAdapterModel(moviesList: UiUpcomingMovieModel): List<AdapterComingSoonMovieModel> {
        val adapterMovieList = mutableListOf<AdapterComingSoonMovieModel>()

        moviesList.results.forEach {
            val movie = AdapterComingSoonMovieModel(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate
            )

            adapterMovieList.add(movie)
        }

        return adapterMovieList
    }

    override fun movieGetClicked() {

    }
}