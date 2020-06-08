package com.edgar.movie.ui.rank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.edgar.movie.R

class RankFragment : Fragment()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val root = inflater.inflate(R.layout.fragment_rank, container, false)
        root.findViewById<View>(R.id.view_rank_new).setOnClickListener {it -> onViewClick(it.id)}
        root.findViewById<View>(R.id.view_rank_week).setOnClickListener {it -> onViewClick(it.id)}
        root.findViewById<View>(R.id.view_rank_us).setOnClickListener {it -> onViewClick(it.id)}
        root.findViewById<View>(R.id.view_rank_top).setOnClickListener {it -> onViewClick(it.id)}
        return root
    }

    private fun onViewClick(id:Int) {
        when(id) {
            R.id.view_rank_new -> goToNext("new")
            R.id.view_rank_top -> goToNext("top")
            R.id.view_rank_week -> goToNext("week")
            R.id.view_rank_us -> goToNext("us")
        }
    }

    private fun goToNext(action:String) {

    }
}