package com.mariana.moviedbpi.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mariana.moviedbpi.data.model.database.MovieDB

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun getFavoriteMovies(): List<MovieDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovie(movie: MovieDB)

    @Delete
    fun deleteFavoriteMovie(movie: MovieDB)
}