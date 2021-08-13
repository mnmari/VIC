package com.mariana.moviedbpi.data.database

import androidx.room.*
import com.mariana.moviedbpi.data.model.database.MovieDB

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movies ORDER BY title")
    fun getFavoriteMovies(): List<MovieDB>?

    @Query("SELECT * FROM favorite_movies WHERE id = :movieID")
    fun getFavoriteMovieFromId(movieID: Int): List<MovieDB>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovie(movie: MovieDB)

    @Delete
    fun deleteFavoriteMovie(movie: MovieDB)
}