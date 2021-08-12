package com.mariana.moviedbpi.domain

interface OnFavoriteDeletedListener {

    fun onFavoriteDeleted(position: Int)
    fun onGenreChanged()
}