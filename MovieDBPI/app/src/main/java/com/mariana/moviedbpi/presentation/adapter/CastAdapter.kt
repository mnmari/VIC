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
import com.mariana.moviedbpi.domain.entity.Cast

class CastAdapter(private val context: Context, var dataSet: MutableList<Cast> = mutableListOf()) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    inner class CastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemCastPicture: ImageView = view.findViewById(R.id.imgCast)
        val itemCastName: TextView = view.findViewById(R.id.txtCastName)
        val itemCastRole: TextView = view.findViewById(R.id.txtCastRole)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_cast, viewGroup, false)

        return CastViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CastViewHolder, position: Int) {
        viewHolder.itemCastName.text = dataSet[position].name
        viewHolder.itemCastRole.text = dataSet[position].role

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${dataSet[position].profilePicturePath}")
            .placeholder(R.drawable.cast_1)
            .into(viewHolder.itemCastPicture)
    }

    override fun getItemCount() = dataSet.size
}