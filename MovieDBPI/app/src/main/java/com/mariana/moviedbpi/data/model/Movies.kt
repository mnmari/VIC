package com.mariana.moviedbpi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularMovies(
    @SerializedName("results")
    val popularMovies: List<Movie>
) : Parcelable

@Parcelize
data class Movie(

    @SerializedName("movie_id")
    val movieID: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("genres")
    val genres: List<Genres>,

    @SerializedName("title")
    val title: String,

    @SerializedName("year")
    val year: String,

    @SerializedName("vote_average")
    val userRating: Float,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("overview")
    val overview: String ) : Parcelable {

    fun showUserRatingString() : String {
        return "${"%.0f".format((userRating * 10.0))}%"
    }
}