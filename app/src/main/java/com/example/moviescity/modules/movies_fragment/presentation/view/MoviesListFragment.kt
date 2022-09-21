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
import androidx.fragment.app.Fragment
import com.example.moviescity.R
import com.example.moviescity.databinding.FragmentMoviesListBinding


class MoviesListFragment : Fragment() {
    private lateinit var binding: FragmentMoviesListBinding

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

}