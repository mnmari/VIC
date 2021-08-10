package com.mariana.moviedbpi.data.database

import android.app.Application
import androidx.room.Room

class DatabaseBuilder : Application() {

    companion object {
        var database: MovieDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(applicationContext, MovieDatabase::class.java, "movie_db").fallbackToDestructiveMigration().build()
    }
}