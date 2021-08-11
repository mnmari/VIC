package com.mariana.moviedbpi.data.mapper.database

import com.google.gson.Gson
import com.mariana.moviedbpi.data.model.database.MovieDB
import com.mariana.moviedbpi.domain.entity.Movie

class MoviesListDBMapper{

    fun mapMoviesFromDatabaseToEntity(movieDB : MovieDB) = Movie(
        movieID = movieDB.movieID,
        posterPath = movieDB.posterPath,
        genreIDs = Gson().fromJson(movieDB.genreIDs, Array<Int>::class.java).toList(),
        title = movieDB.title,
        releaseDate = movieDB.releaseDate,
        userRating = movieDB.userRating,
        overview = movieDB.overview,
        isFavorite = true)

    fun mapMoviesFromEntityToDatabase(movie : Movie) = MovieDB(
        movieID = movie.movieID,
        posterPath = movie.posterPath,
        genreIDs = Gson().toJson(movie.genreIDs),
        title = movie.title,
        releaseDate = movie.releaseDate,
        userRating = movie.userRating,
        overview = movie.overview)
}