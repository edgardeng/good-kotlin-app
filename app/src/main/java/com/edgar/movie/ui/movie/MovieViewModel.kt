package com.edgar.movie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edgar.movie.data.model.Movie
import com.edgar.movie.data.model.MovieImage

class MovieViewModel : ViewModel() {

    val movies: LiveData<List<Movie>> = MutableLiveData<List<Movie>>().apply {
        // TODO

        val mim = MovieImage("https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2549234765.webp",
        "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2549234765.webp",
        "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2549234765.webp")

        val m = Movie("小飞象真人版", "2020", "11" , mim)
        value = listOf(m)


    }
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is Movie Fragment"
//    }
//    val text: LiveData<String> = _text


//    fun (num: Int) {
//        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, num)
//    }

}