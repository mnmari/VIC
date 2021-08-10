package com.mariana.moviedbpi.data.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PopularMoviesDB(
    val popularMovies: List<MovieDB>
)

data class SearchMoviesDB(
    val foundMovies: List<MovieDB>
)

@Entity(tableName = "favorite_movies")
data class MovieDB(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val movieID: Int,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = "",

    @ColumnInfo(name = "genres_ids")
    val genreIDs: String? = "",

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "user_rating")
    val userRating: Float,

    @ColumnInfo(name = "overview")
    val overview: String
)
