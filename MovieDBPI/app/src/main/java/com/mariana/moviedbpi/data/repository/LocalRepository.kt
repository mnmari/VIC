package com.mariana.moviedbpi.data.repository

import com.mariana.moviedbpi.data.database.DatabaseBuilder
import com.mariana.moviedbpi.data.mapper.database.MoviesListDBMapper
import com.mariana.moviedbpi.domain.entity.Movie

class LocalRepository(
    private val moviesMapper: MoviesListDBMapper = MoviesListDBMapper()
) {

    suspend fun getFavoriteMovies() : List<Movie>? {
        return DatabaseBuilder.database?.moviesDao()?.getFavoriteMovies()?.map { moviesMapper.mapMoviesFromDatabaseToEntity(it) }
    }

    suspend fun addFavoriteMovie(movie: Movie) : Movie {
        DatabaseBuilder.database?.moviesDao()?.addFavoriteMovie(moviesMapper.mapMoviesFromEntityToDatabase(movie))
        return movie
    }

    suspend fun deleteFavoriteMovie(movie: Movie) : Movie {
        DatabaseBuilder.database?.moviesDao()?.deleteFavoriteMovie(moviesMapper.mapMoviesFromEntityToDatabase(movie))
        return movie
    }
}