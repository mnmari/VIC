package com.mariana.moviedbpi.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.domain.MovieActionListener
import com.mariana.moviedbpi.domain.entity.GenresEnum

class GenresAdapter(private val context: Context, private val movieActionListener: MovieActionListener? = null, var dataSet: MutableList<Int> = mutableListOf()) : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private val selectedGenres: MutableList<Int> = mutableListOf()

    inner class GenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemGenre: Chip? = view.findViewById(R.id.itemGenre)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GenresViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_genre, viewGroup, false)

        return GenresViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: GenresViewHolder, position: Int) {
        val genre = getGenreNameById(dataSet[position])

        viewHolder.itemGenre?.text = genre.description

        viewHolder.itemGenre?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedGenres.add(dataSet[position])
            } else {
                selectedGenres.remove(dataSet[position])
            }
            movieActionListener?.filterMoviesByGenre(selectedGenres)
        }

    }

    fun getGenreNameById(id: Int) : GenresEnum {
        return GenresEnum.values().find { it.id == id }?: GenresEnum.ALL
    }

    override fun getItemCount() = dataSet.size
}