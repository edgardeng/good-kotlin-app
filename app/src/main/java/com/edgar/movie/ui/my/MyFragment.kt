package com.edgar.movie.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.edgar.movie.R

class MyFragment : Fragment() {

    private lateinit var myViewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myViewModel =
            ViewModelProviders.of(this).get(MyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cinema, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        myViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}