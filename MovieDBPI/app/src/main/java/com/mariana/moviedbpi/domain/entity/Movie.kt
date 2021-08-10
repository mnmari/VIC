package com.mariana.moviedbpi.domain.entity

data class PopularMovies(
    val popularMovies: List<Movie>
)

data class SearchMovies(
    val foundMovies: List<Movie>
)

data class Movie(

    val movieID: Int,
    val posterPath: String? = "",
    val genreIDs: List<Int>,
    val title: String,
    val releaseDate: String,
    val userRating: Float,
    val overview: String,
    var isFavorite: Boolean = false ){

    fun showUserRatingString() : String {
        return "${"%.0f".format((userRating * 10.0))}%"
    }
}