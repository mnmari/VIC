package com.mariana.moviedbpi.data.mapper.database

import com.google.gson.Gson
import com.mariana.moviedbpi.data.model.database.MovieDB
import com.mariana.moviedbpi.domain.entity.MovieDetail

class MoviesDetailDBMapper{

    fun mapMoviesFromDatabaseToEntity(movieDB : MovieDB) = MovieDetail(
        movieID = movieDB.movieID,
        posterPath = movieDB.posterPath,
        genres = listOf(),
        title = movieDB.title,
        releaseDate = movieDB.releaseDate,
        userRating = movieDB.userRating,
        overview = movieDB.overview,
        runtime = 0,
        isFavorite = true)

    fun mapMoviesFromEntityToDatabase(movie : MovieDetail) = MovieDB(
        movieID = movie.movieID,
        posterPath = movie.posterPath,
        genreIDs = Gson().toJson(movie.genres?.map { it.genreID }),
        title = movie.title,
        releaseDate = movie.releaseDate,
        userRating = movie.userRating,
        overview = movie.overview)
}