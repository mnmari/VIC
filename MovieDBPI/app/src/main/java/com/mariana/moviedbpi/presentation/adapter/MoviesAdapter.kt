package com.mariana.moviedbpi.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.domain.MovieActionListener
import com.mariana.moviedbpi.domain.entity.Movie

class MoviesAdapter(private val context: Context, private val movieActionListener: MovieActionListener? = null, var dataSet: MutableList<Movie> = mutableListOf()) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemMoviePoster: ImageButton = view.findViewById(R.id.imgMoviePoster)
        val itemMovieName: TextView = view.findViewById(R.id.txtMovieName)
        val itemMovieUserRating: TextView = view.findViewById(R.id.txtMovieUserRating)
        val itemIsFavorite: ImageButton = view.findViewById(R.id.btnFavoriteIcon)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_movies, viewGroup, false)

        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MoviesViewHolder, position: Int) {

        viewHolder.itemMovieName.text = dataSet[position].title
        viewHolder.itemMovieUserRating.text = dataSet[position].showUserRatingString()

        if (dataSet[position].isFavorite)
            viewHolder.itemIsFavorite.setImageResource(R.drawable.ic_heart_favorites_selected)

        else
            viewHolder.itemIsFavorite.setImageResource(R.drawable.ic_heart_favorites_unselected)

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${dataSet[position].posterPath}")
            .into(viewHolder.itemMoviePoster)

        viewHolder.itemMoviePoster.setOnClickListener {
            movieActionListener?.openMovieDetailActivity(dataSet[position].movieID)
        }

        viewHolder.itemIsFavorite.setOnClickListener {
            movieActionListener?.onFavoriteClickedListener(dataSet[position], true, position)
        }

    }

    //onViewRecycled()

    override fun getItemCount() = dataSet.size
}