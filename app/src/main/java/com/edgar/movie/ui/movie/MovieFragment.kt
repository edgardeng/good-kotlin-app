package com.edgar.movie.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edgar.movie.adapter.MovieGridAdapter
import com.edgar.movie.databinding.FragmentMovieListBinding


class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_movie, container, false)
//        val textView: TextView = root.findViewById(R.id.movie_text)
//        movieViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root

        val binding = FragmentMovieListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MovieGridAdapter()
        binding.movieList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }


    private fun subscribeUi(adapter: MovieGridAdapter) {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}