package com.mariana.moviedbpi.domain

import com.mariana.moviedbpi.domain.entity.Movie

interface MovieActionListener {

    fun openMovieDetailActivity(movieID: Int)
    fun filterMoviesByGenre(genresIDs: MutableList<Int>)
    fun onFavoriteClickedListener(movie: Movie, isClicked: Boolean)
}