package com.example.moviescity.modules.movies_fragment.presentation.view


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescity.R
import com.example.moviescity.databinding.FragmentMoviesListBinding
import com.example.moviescity.di.DaggerViewModelFactory
import com.example.moviescity.modules.coming_soon_movies_adapter.presentation.model.AdapterComingSoonMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.presentation.model.UiUpcomingMovieModel
import com.example.moviescity.modules.coming_soon_movies_list.presentation.viewmodel.UpcomingMoviesViewModel
import com.example.moviescity.modules.movies_fragment.presentation.adapter.ViewsRecyclerAdapter
import com.example.moviescity.modules.movies_list.presentation.model.EnumMoviesType
import com.example.moviescity.modules.movies_list.presentation.model.ViewMovieModel
import com.example.moviescity.modules.movies_list.presentation.viewmodel.MoviesListViewModel
import com.example.moviescity.modules.movies_list_adapter.presentation.model.AdapterMovieModel
import com.example.moviescity.modules.movies_list_adapter.presentation.view.MovieClickListener
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MoviesListFragment : DaggerFragment(), MovieClickListener, MovieTypeClickListener {
    private val TAG = "MoviesListFragment"

    private lateinit var binding: FragmentMoviesListBinding

    @Inject
    lateinit var viewsRecyclerAdapter: ViewsRecyclerAdapter

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private lateinit var moviesListViewModel: MoviesListViewModel
    private lateinit var upComingSoonMoviesListViewModel: UpcomingMoviesViewModel

    private var viewsSize = 0
    private var filterValue = EnumMoviesType.DISCOVER_MOVIES

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

        initViews()
        initViewModels()
        setupListeners()
        getData()
        initObservers()
    }

    override fun movieGetClicked() {

    }

    override fun onFilterSelected(view: View) {
        showPopup(view)
    }

    private fun initViews() {
        binding.viewsRecyclerview.visibility = View.INVISIBLE
        binding.viewsRecyclerview.adapter = viewsRecyclerAdapter
        viewsRecyclerAdapter.updateFilterText("Discover Movies")
    }

    private fun initViewModels() {
        moviesListViewModel =
            ViewModelProvider(this, daggerViewModelFactory)[MoviesListViewModel::class.java]

        upComingSoonMoviesListViewModel =
            ViewModelProvider(this, daggerViewModelFactory)[UpcomingMoviesViewModel::class.java]
    }

    private fun setupListeners() {
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

        binding.viewsRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager

                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() >= viewsSize - 1 && viewsSize > 4) {
                    moviesListViewModel.getNextPage(filterValue, true)
                }
            }
        })

        binding.placeholderNoInternet.retryInternetButton.setOnClickListener {
            binding.loadingProgressBar.visibility = View.VISIBLE
            binding.placeholderNoInternet.root.visibility = View.GONE

            getData()
        }
    }

    private fun getData() {
        binding.loadingProgressBar.visibility = View.VISIBLE
        upComingSoonMoviesListViewModel.getUpcomingMovies()
        moviesListViewModel.getNextPage(EnumMoviesType.DISCOVER_MOVIES, false)
    }

    private fun initObservers() {
        moviesListViewModel.movies.observe(viewLifecycleOwner) {
            binding.loadingProgressBar.visibility = View.GONE
            if (it == null) {
                binding.viewsRecyclerview.visibility = View.INVISIBLE
                binding.placeholderNoMovies.root.visibility = View.GONE
                binding.placeholderNoInternet.root.visibility = View.VISIBLE
            } else {
                if (it.movies.isNotEmpty()) {
                    binding.viewsRecyclerview.visibility = View.VISIBLE
                    binding.placeholderNoMovies.root.visibility = View.GONE
                    binding.placeholderNoInternet.root.visibility = View.GONE


                    var movies = moviesToAdapterModel(it)
                    if (it.paginated) {
                        val newMovies =
                            viewsRecyclerAdapter.getLatestUpdatedMovies().toMutableList()
                        newMovies.addAll(movies)
                        movies = newMovies
                        viewsSize += it.movies.size
                    } else {
                        viewsSize = it.movies.size + 1
                    }
                    viewsRecyclerAdapter.updateMovies(movies)
                } else {
                    binding.viewsRecyclerview.visibility = View.INVISIBLE
                    binding.placeholderNoMovies.root.visibility = View.VISIBLE
                    binding.placeholderNoInternet.root.visibility = View.GONE
                }
            }
        }

        upComingSoonMoviesListViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.results.isNotEmpty()) {
                    (binding.viewsRecyclerview.layoutManager as GridLayoutManager).spanSizeLookup =
                        object : SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return if (position == 0 || position == 1) {
                                    2
                                } else {
                                    1
                                }
                            }
                        }
                    viewsRecyclerAdapter.updateUpcomingMovies(upcomingMoviesToAdapterModel(it))
                    viewsSize += 1
                }
            }
        }

        moviesListViewModel.isConnected.observe(viewLifecycleOwner) {
            if (!it) {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.apply {
                    //setIcon(R.drawable.ic_hello)
                    setTitle("Internet Connection")
                    setMessage("No Internet Connection, Please try again later")
                    setPositiveButton("Okay") { _: DialogInterface?, _: Int -> }
                }.create().show()
            }
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

    private fun showPopup(view: View) {
        val popup = PopupMenu(context, view)
        // Inflate the menu from xml
        popup.menuInflater.inflate(R.menu.menu_filter_movies, popup.menu)
        // Setup menu item selection
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.discover_filter -> {
                    filterValue = EnumMoviesType.DISCOVER_MOVIES
                    viewsRecyclerAdapter.updateFilterText("Discover Movies")
                    moviesListViewModel.getNextPage(filterValue, false)
                    true
                }
                R.id.popular_filter -> {
                    filterValue = EnumMoviesType.POPULAR_MOVIES
                    viewsRecyclerAdapter.updateFilterText("Popular Movies")
                    moviesListViewModel.getNextPage(filterValue, false)
                    true
                }
                R.id.top_rated_filter -> {
                    filterValue = EnumMoviesType.TOP_RATED_MOVIES
                    viewsRecyclerAdapter.updateFilterText("Top Rated Movies")
                    moviesListViewModel.getNextPage(filterValue, false)
                    true
                }
                else -> false
            }
        }
        // Handle dismissal with: popup.setOnDismissListener(...);
        // Show the menu
        popup.show()
    }
}