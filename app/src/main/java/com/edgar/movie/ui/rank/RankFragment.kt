package com.edgar.movie.ui.rank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.edgar.movie.R

class RankFragment : Fragment() {

    private lateinit var rankViewModel: RankViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rankViewModel =
            ViewModelProviders.of(this).get(RankViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        rankViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}