/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.edgar.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edgar.movie.data.model.Movie
import com.edgar.movie.databinding.GridItemMovieBinding

//import com.google.samples.apps.sunflower.HomeViewPagerFragmentDirections
//import com.google.samples.apps.sunflower.PlantListFragment
//import com.google.samples.apps.sunflower.data.Plant
//import com.google.samples.apps.sunflower.databinding.ListItemPlantBinding

/**
 * Adapter for the [RecyclerView] in [PlantListFragment].
 */
class MovieGridAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(GridItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

    class PlantViewHolder(
        private val binding: GridItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.movie?.let { model ->
                    navigateToMovie(model, it)
                }
            }
        }

        private fun navigateToMovie( plant: Movie, view: View ) {
//            val direction =
//                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
//                    plant.plantId
//                )
//            view.findNavController().navigate(direction)
        }

        fun bind(item: Movie) {
            binding.apply {
                movie = item
                executePendingBindings()
            }
        }
    }
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}