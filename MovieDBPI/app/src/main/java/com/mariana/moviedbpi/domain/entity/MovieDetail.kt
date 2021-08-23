package com.mariana.moviedbpi.domain.entity

data class MovieDetail(

    var movieID: Int,
    val posterPath: String? = "",
    val genres: List<Genres>? = listOf(),
    val title: String,
    val releaseDate: String,
    val userRating: Double,
    val runtime: Int,
    val overview: String,
    var isFavorite: Boolean = false ){

    fun showUserRatingString() : String {
        return "${"%.0f".format((userRating * 10.0))}%"
    }

    fun showYearFromDate() : String {
        return releaseDate.take(4)
    }

    fun showRuntimeInHoursAndMinutes() : String {
        val hours = runtime/60
        val minutes = runtime%60

        return "%dh %dmin".format(hours, minutes)
    }
}