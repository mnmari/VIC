package com.mariana.moviedbpi.data.model.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

    @SerializedName("id")
    val movieID: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("genres")
    val genres: List<GenresResponse>? = listOf(),

    @SerializedName("title")
    val title: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val userRating: Float,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("overview")
    val overview: String ) {
}