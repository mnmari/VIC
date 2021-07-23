package com.mariana.moviedbpi.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.data.model.Movie

class MoviesAdapter(val context: Context, var dataSet: MutableList<Movie> = mutableListOf()) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemMoviePoster: ImageView = view.findViewById(R.id.imgMoviePoster)
        var itemMovieName: TextView = view.findViewById(R.id.txtMovieName)
        var itemMovieUserRating: TextView = view.findViewById(R.id.txtMovieUserRating)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movies, viewGroup, false)

        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MoviesViewHolder, position: Int) {

        viewHolder.itemMovieName.text = dataSet[position].title
        viewHolder.itemMovieUserRating.text = dataSet[position].showUserRatingString()

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${dataSet[position].posterPath}")
            .into(viewHolder.itemMoviePoster)
    }

    //onViewRecycled()

    override fun getItemCount() = dataSet.size



}