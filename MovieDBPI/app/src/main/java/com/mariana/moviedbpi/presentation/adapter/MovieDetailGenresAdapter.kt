package com.mariana.moviedbpi.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.domain.entity.Genres

class MovieDetailGenresAdapter(var dataSet: MutableList<Genres> = mutableListOf()) : RecyclerView.Adapter<MovieDetailGenresAdapter.MovieDetailGenresViewHolder>() {

    inner class MovieDetailGenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemGenreName: TextView = view.findViewById(R.id.itemGenreMovieDetail)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieDetailGenresViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_genre_movie_detail, viewGroup, false)

        return MovieDetailGenresViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MovieDetailGenresViewHolder, position: Int) {
        viewHolder.itemGenreName.text = dataSet[position].name
    }

    override fun getItemCount() = dataSet.size
}