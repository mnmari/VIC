package com.mariana.moviedbpi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mariana.moviedbpi.data.model.database.MovieDB

@Database(entities = [MovieDB::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao() : MovieDao
}