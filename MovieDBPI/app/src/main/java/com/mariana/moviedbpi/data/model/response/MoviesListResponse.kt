package com.mariana.moviedbpi.data.model.response

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("results")
    val popularMovies: List<MovieResponse>
)

data class SearchMoviesResponse(
    @SerializedName("results")
    val foundMovies: List<MovieResponse>
)

data class MovieResponse(

    @SerializedName("id")
    val movieID: Int,

    @SerializedName("poster_path")
    val posterPath: String? = "",

    @SerializedName("genre_ids")
    val genreIDs: List<Int>,

    @SerializedName("title")
    val title: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val userRating: Float,

    @SerializedName("overview")
    val overview: String ) {
}