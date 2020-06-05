package com.edgar.movie.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edgar.movie.R

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel =
            ViewModelProviders.of(this).get(MovieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        val textView: TextView = root.findViewById(R.id.movie_text)
        movieViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}