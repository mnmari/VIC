package com.mariana.moviedbpi.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.data.model.Genres

class GenresAdapter(var dataSet: MutableList<Genres> = mutableListOf()) : RecyclerView.Adapter<GenresAdapter.FilterViewHolder>() {

    inner class FilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemFilterName: TextView = view.findViewById(R.id.itemGenre)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_genre, viewGroup, false)

        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FilterViewHolder, position: Int) {
        viewHolder.itemFilterName.text = dataSet[position].name
    }

    override fun getItemCount() = dataSet.size
}